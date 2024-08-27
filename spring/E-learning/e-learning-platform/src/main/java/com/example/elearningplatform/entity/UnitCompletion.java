package com.example.elearningplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class UnitCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_content_id")
    private CourseContent courseContent;

    private boolean completed;

    private LocalDateTime completionDate;
}
