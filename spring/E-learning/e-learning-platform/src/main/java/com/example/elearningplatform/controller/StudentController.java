package com.example.elearningplatform.controller;

import com.example.elearningplatform.dto.CourseDTO;
import com.example.elearningplatform.entity.Course;
import com.example.elearningplatform.entity.Review;
import com.example.elearningplatform.service.CourseService;
import com.example.elearningplatform.service.EnrollmentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/enroll/{id}")
    public ResponseEntity<String> enrollCourse(@PathVariable int id,
                                               @RequestParam(required = false) String cardNumber,
                                               @RequestParam(required = false) String cardExpiry,
                                               @RequestParam(required = false) String cardCVC) {
        return enrollmentService.enrollCourse(id, cardNumber, cardExpiry, cardCVC);
    }
    @GetMapping("/courses")
    public List<CourseDTO> getApprovedCourses(){
        return courseService.getAllApprovedCourses();
    }

    @PostMapping("/content-complete/{contentId}")
    public ResponseEntity<String> markUnitAsCompleted(@PathVariable int contentId) {
        return courseService.markUnitAsCompleted(contentId);
    }
    @GetMapping("/progress/{courseId}")
    public ResponseEntity<Double> getCourseProgress(@PathVariable int courseId) {
        return courseService.getCourseProgress(courseId);
    }

    @PostMapping("/rete/{courseId}")
    public ResponseEntity<String> giveReview(@PathVariable int courseId, @RequestBody Review review) {
        return courseService.giveReview(courseId, review.getComment(), review.getRating());
    }

    @GetMapping("/reviews/{courseId}")
    public List<Review> getReviews(@PathVariable int courseId) {
        return courseService.getReviews(courseId);
    }
}
