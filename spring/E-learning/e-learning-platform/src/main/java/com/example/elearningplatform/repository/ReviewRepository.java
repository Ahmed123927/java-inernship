package com.example.elearningplatform.repository;

import com.example.elearningplatform.entity.Course;
import com.example.elearningplatform.entity.Review;
import com.example.elearningplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
 Optional<Review> findByStudentAndCourse(User student, Course course);

    List<Review> findAllByCourse(Course course);
}
