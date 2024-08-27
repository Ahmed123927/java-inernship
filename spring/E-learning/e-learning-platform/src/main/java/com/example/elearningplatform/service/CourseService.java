package com.example.elearningplatform.service;

import com.example.elearningplatform.dto.CourseDTO;
import com.example.elearningplatform.dto.mapping.CourseMapper;
import com.example.elearningplatform.entity.*;
import com.example.elearningplatform.options.CourseStatus;
import com.example.elearningplatform.repository.CourseContentRepository;
import com.example.elearningplatform.repository.CourseRepository;
import com.example.elearningplatform.repository.ReviewRepository;
import com.example.elearningplatform.repository.UnitCompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseContentRepository courseContentRepository;

    @Autowired
    private UnitCompletionRepository unitCompletionRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> addCourse(Course course) {
        course.setStatus(CourseStatus.PENDING);
        courseRepository.save(course);
        return ResponseEntity.ok("Course added successfully.");
    }

    @Transactional
    public ResponseEntity<String> deleteCourse(int id) {
        Optional<Course> course = courseRepository.findById(id);
        if (!course.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        courseRepository.deleteById(id);
        return ResponseEntity.ok("Course deleted successfully.");
    }

    public CourseDTO getCourseById(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        return CourseMapper.toDTO(course);
    }

    public ResponseEntity<String> updateCourse(CourseDTO courseDTO) {
        Course course = CourseMapper.toEntity(courseDTO);
        courseRepository.save(course);
        return ResponseEntity.ok("Course updated successfully.");
    }

    @Transactional
    public List<CourseDTO> getAllPendingCourses() {
        List<Course> courses = courseRepository.findAllByStatus(CourseStatus.PENDING);
        return courses.stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CourseDTO> getAllApprovedCourses() {
        return courseRepository.findAllByStatus(CourseStatus.APPROVED).stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CourseDTO> getAllCoursesByInstructor(Integer id) {
        return courseRepository.findAllByInstructorId(id).stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> approveCourse(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        course.setStatus(CourseStatus.APPROVED);
        courseRepository.save(course);
        return ResponseEntity.ok("Course Approved");
    }

    public ResponseEntity<String> rejectCourse(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        course.setStatus(CourseStatus.REJECTED);
        courseRepository.save(course);
        return ResponseEntity.ok("Course Rejected");
    }

    public ResponseEntity<String> addCourseContent(int id, CourseContent courseContent) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        courseContent.setCourse(course);
        courseContentRepository.save(courseContent);
        return ResponseEntity.ok("Course content added successfully.");
    }

    public List<CourseContent> getCourseContent(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new RuntimeException("Course not found");
        }
        return courseContentRepository.findAllByCourse(course);
    }

    public CourseContent getCourseContentById(int id) {
        return courseContentRepository.findById(id).orElse(null);
    }

    public ResponseEntity<String> markUnitAsCompleted(int contentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User student = (User) authentication.getPrincipal();

        CourseContent courseContent = courseContentRepository.findById(contentId).orElseThrow();

        UnitCompletion existingCompletion = unitCompletionRepository.findByStudentAndCourseContent(student, courseContent)
                .orElse(null);

        if (existingCompletion != null && existingCompletion.isCompleted()) {
            return new ResponseEntity<>("Unit already marked as completed.", HttpStatus.CONFLICT);
        }

        UnitCompletion unitCompletion = new UnitCompletion();
        unitCompletion.setStudent(student);
        unitCompletion.setCourseContent(courseContent);
        unitCompletion.setCompleted(true);
        unitCompletion.setCompletionDate(LocalDateTime.now());

        unitCompletionRepository.save(unitCompletion);

        return new ResponseEntity<>("Unit marked as completed successfully.", HttpStatus.OK);
    }

    public boolean isCourseCompleted(int courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User student = (User) authentication.getPrincipal();

        Course course = courseRepository.findById(courseId)
                .orElseThrow();

        List<UnitCompletion> completedUnits = unitCompletionRepository.findByStudentAndCourseContent_Course(student, course);

        return completedUnits.size() == course.getContents().size();
    }

    public ResponseEntity<Double> getCourseProgress(int courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User student = (User) authentication.getPrincipal();

        Course course = courseRepository.findById(courseId)
                .orElseThrow();

        int totalUnits = course.getContents().size();
        if (totalUnits == 0) {
            return new ResponseEntity<>(0.0, HttpStatus.OK);
        }

        List<UnitCompletion> completedUnits = unitCompletionRepository.findByStudentAndCourseContent_Course(student, course);
        int completedUnitsCount = completedUnits.size();

        double progress = (double) completedUnitsCount / totalUnits * 100;

        return new ResponseEntity<>(progress, HttpStatus.OK);
    }

    public ResponseEntity<String> giveReview(int courseId, String message, int rate) {
        if (!isCourseCompleted(courseId)) {
            return new ResponseEntity<>("Course not completed yet.", HttpStatus.CONFLICT);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User student = (User) authentication.getPrincipal();

        Course course = courseRepository.findById(courseId)
                .orElseThrow();

        Review review = reviewRepository.findByStudentAndCourse(student, course)
                .orElse(null);

        if (review != null) {
            return new ResponseEntity<>("Review already given.", HttpStatus.CONFLICT);
        }

        review = new Review();
        review.setStudent(student);
        review.setCourse(course);
        review.setRating(rate);
        review.setComment(message);

        reviewRepository.save(review);

        return new ResponseEntity<>("Review given successfully.", HttpStatus.OK);
    }

    public List<Review> getReviews(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new RuntimeException("Course not found");
        }
        return reviewRepository.findAllByCourse(course);
    }

    public ResponseEntity<String> removeReview(int id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (!review.isPresent()) {
            return new ResponseEntity<>("Review not found.", HttpStatus.NOT_FOUND);
        }
        reviewRepository.deleteById(id);
        return ResponseEntity.ok("Review Deleted Successfully");
    }
}
