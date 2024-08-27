package com.example.elearningplatform.entity;

import com.example.elearningplatform.entity.Course;
import com.example.elearningplatform.entity.UnitCompletion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class CourseContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String type;
    private String url;
    private String text;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "courseContent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UnitCompletion> unitCompletions = new ArrayList<>();
}
