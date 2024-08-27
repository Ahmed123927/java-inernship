package com.example.elearningplatform.repository;

import com.example.elearningplatform.entity.Course;
import com.example.elearningplatform.entity.CourseContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseContentRepository extends JpaRepository<CourseContent,Integer> {
    List<CourseContent> findAllByCourse(Course course);
}
