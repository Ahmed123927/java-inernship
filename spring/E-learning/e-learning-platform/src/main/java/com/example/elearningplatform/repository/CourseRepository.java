package com.example.elearningplatform.repository;

import com.example.elearningplatform.entity.Course;
import com.example.elearningplatform.entity.User;
import com.example.elearningplatform.options.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findAllByStatus(CourseStatus status);

    List<Course> findAllByInstructorId(Integer instructorId);
}
