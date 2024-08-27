package com.example.elearningplatform.service;

import com.example.elearningplatform.dto.UserDto;
import com.example.elearningplatform.dto.mapping.UserMapper;
import com.example.elearningplatform.entity.Course;
import com.example.elearningplatform.entity.Enrollment;
import com.example.elearningplatform.entity.User;
import com.example.elearningplatform.repository.CourseRepository;
import com.example.elearningplatform.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public ResponseEntity<String> enrollCourse(int id,
                                               @RequestParam(required = false) String cardNumber,
                                               @RequestParam(required = false) String cardExpiry,
                                               @RequestParam(required = false) String cardCVC) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with id " + id + " not found");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        User currentUser = (User) authentication.getPrincipal();
        boolean alreadyEnrolled = enrollmentRepository.existsByCourseAndUser(course, currentUser);
        if (alreadyEnrolled) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("u enrolled in this course");
        }

        if (!course.isFree()) {
            if (cardNumber == null || cardExpiry == null || cardCVC == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please pay");
            }

            boolean paymentSuccessful = processPayment(currentUser, course, cardNumber, cardExpiry, cardCVC);
            if (!paymentSuccessful) {
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Payment failed");
            }
        }

        Enrollment enrollment = Enrollment.builder()
                .course(course)
                .user(currentUser)
                .enrollmentDate(LocalDateTime.now())
                .build();

        enrollmentRepository.save(enrollment);

        return ResponseEntity.ok("Enrollment successful");
    }

    private boolean processPayment(User user, Course course, String cardNumber, String cardExpiry, String cardCVC) {
        System.out.println("Processing payment for user: " + user.getUsername() + ", course: " + course.getTitle());
        System.out.println("Card Details: Number = " + cardNumber + ", Expiry = " + cardExpiry + ", CVC = " + cardCVC);

        boolean paymentSuccessful = true;


        if (paymentSuccessful) {
            System.out.println("Payment successful for user: " + user.getUsername());
        } else {
            System.out.println("Payment failed for user: " + user.getUsername());
        }

        return paymentSuccessful;
    }

    public List<UserDto> getUsersByCourseId(int courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
        return enrollments.stream()
                .map(enrollment -> UserMapper.toDto(enrollment.getUser()))
                .collect(Collectors.toList());
    }

}
