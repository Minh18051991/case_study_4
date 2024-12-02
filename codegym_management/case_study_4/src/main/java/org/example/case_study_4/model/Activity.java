package org.example.case_study_4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "activity")
@Getter
@Setter
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "is_delete")
    private Boolean isDelete = false;

    @ManyToOne
    @JoinColumn(name = "category_activity_id")
    private CategoryActivity category;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}