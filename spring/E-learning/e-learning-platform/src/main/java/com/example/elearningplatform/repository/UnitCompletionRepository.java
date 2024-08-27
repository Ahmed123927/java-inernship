package com.example.elearningplatform.repository;

import com.example.elearningplatform.entity.Course;
import com.example.elearningplatform.entity.UnitCompletion;
import com.example.elearningplatform.entity.User;
import com.example.elearningplatform.entity.CourseContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnitCompletionRepository extends JpaRepository<UnitCompletion, Long> {
    Optional<UnitCompletion> findByStudentAndCourseContent(User student, CourseContent courseContent);
    List<UnitCompletion> findByStudentAndCourseContent_Course(User student, Course course);
}
