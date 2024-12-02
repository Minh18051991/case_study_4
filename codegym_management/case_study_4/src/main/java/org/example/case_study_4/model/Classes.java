package org.example.case_study_4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "class")
@Getter
@Setter
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "is_delete")
    private Boolean isDelete = false;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}