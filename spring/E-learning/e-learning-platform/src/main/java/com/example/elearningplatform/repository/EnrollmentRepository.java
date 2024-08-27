package com.example.elearningplatform.repository;

import com.example.elearningplatform.entity.Course;
import com.example.elearningplatform.entity.Enrollment;
import com.example.elearningplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
    public boolean existsByCourseAndUser(Course course, User user);
    List<Enrollment> findByCourseId(Integer course_id);

}
