package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "dialynote")
@Getter
@Setter
public class DailyNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_delete")
    private Boolean isDelete = false;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classEntity;
}