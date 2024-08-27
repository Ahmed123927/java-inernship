package com.example.elearningplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseContentDto {
    private Integer id;
    private String title;
    private String contentType;
    private String url;
}
