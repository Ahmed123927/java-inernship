package com.example.elearningplatform.dto;

import com.example.elearningplatform.options.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Integer id;
    private String title;
    private String description;
    private String category;
    private boolean isFree;
    private Double price;
    private CourseStatus status;
}
