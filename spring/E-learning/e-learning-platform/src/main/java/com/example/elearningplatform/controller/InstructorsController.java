package com.example.elearningplatform.controller;

import com.example.elearningplatform.dto.CourseDTO;
import com.example.elearningplatform.dto.UserDto;
import com.example.elearningplatform.entity.Course;
import com.example.elearningplatform.entity.CourseContent;
import com.example.elearningplatform.entity.User;
import com.example.elearningplatform.repository.UserRepository;
import com.example.elearningplatform.service.CourseService;
import com.example.elearningplatform.service.EnrollmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructors")
public class InstructorsController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  CourseService courseService;
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/addCourse")
    public void addCourse(@RequestBody Course course) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principalUser = (User) authentication.getPrincipal();

        User user = userRepository.findById(principalUser.getId())
                .orElseThrow(   );

        course.setInstructor(user);
        courseService.addCourse(course);
    }

    @GetMapping("/getOwnCourses")
    public List<CourseDTO> getOwnCourses(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return courseService.getAllCoursesByInstructor(user.getId());
    }

    @PostMapping("/updateCourse")
    public void updateCourse(@RequestBody CourseDTO course){
        courseService.updateCourse(course);
    }
    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id){
        return courseService.deleteCourse(id);
    }

    @GetMapping("/enrollments/{id}")
    public List<UserDto> getCourseEnrollments(@PathVariable int id){
        return enrollmentService.getUsersByCourseId(id);
    }

    @PostMapping("/add-content/{id}")
    public ResponseEntity<String> addCourseContent(@PathVariable int id, @RequestBody CourseContent courseContent){
        courseService.addCourseContent(id, courseContent);
        return ResponseEntity.ok("success") ;
    }

    @GetMapping("/course-content/{id}")
    public List<CourseContent> getCourseContent(@PathVariable int id){
        return courseService.getCourseContent(id);
    }
}
