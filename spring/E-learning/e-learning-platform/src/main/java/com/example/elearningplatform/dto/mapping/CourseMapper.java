package com.example.elearningplatform.dto.mapping;

import com.example.elearningplatform.dto.CourseDTO;
import com.example.elearningplatform.entity.Course;

public class CourseMapper {

    // Convert from Course entity to CourseDTO
    public static CourseDTO toDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .category(course.getCategory())
                .isFree(course.isFree())
                .price(course.getPrice())
                .status(course.getStatus())
                // Map other fields as needed
                .build();
    }

    // Convert from CourseDTO to Course entity (if needed)
    public static Course toEntity(CourseDTO courseDTO) {
        return Course.builder()
                .id(courseDTO.getId())
                .title(courseDTO.getTitle())
                .description(courseDTO.getDescription())
                .category(courseDTO.getCategory())
                .isFree(courseDTO.isFree())
                .price(courseDTO.getPrice())
                .status(courseDTO.getStatus())
                // Set other fields if needed
                .build();
    }
}
