package com.example.elearningplatform.controller;

import com.example.elearningplatform.dto.CourseDTO;
import com.example.elearningplatform.dto.UserDto;
import com.example.elearningplatform.entity.Course;
import com.example.elearningplatform.entity.Review;
import com.example.elearningplatform.service.CourseService;
import com.example.elearningplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @GetMapping("/get-users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("/delete-users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.removeUserById(id);
    }

    @GetMapping("/pending-courses")
    public ResponseEntity<List<CourseDTO>> getAllPendingCourses() {
        List<CourseDTO> courses = courseService.getAllPendingCourses();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveCourse(@PathVariable int id) {
        return courseService.approveCourse(id);
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<String> rejectCourse(@PathVariable int id) {
        return courseService.rejectCourse(id);
    }

    @PutMapping("/update-user")
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return ResponseEntity.ok("Successfully updated user");
    }

    @DeleteMapping("/delete-course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        return courseService.deleteCourse(id);
    }

    @GetMapping("/reviews/{courseId}")
    public List<Review> getReviews(@PathVariable int courseId){
        return courseService.getReviews(courseId);
    }

    @DeleteMapping("/delete-review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable int reviewId){
        return courseService.removeReview(reviewId);
    }

    @GetMapping("/all-courses")
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }


    @GetMapping("/approved-courses")
    public List<CourseDTO> getAllApprovedCourses() {
        return courseService.getAllApprovedCourses();
    }

    @GetMapping("/courses-by-instructor/{instructorId}")
    public List<CourseDTO> getAllCoursesByInstructor(@PathVariable int instructorId) {
        return courseService.getAllCoursesByInstructor(instructorId);
    }
}
