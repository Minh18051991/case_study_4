package org.example.case_study_4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
@Getter
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "is_delete")
    private Boolean isDelete = false;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classEntity;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public enum AttendanceStatus {
        VANG_CO_PHEP, CO_MAT, DI_MUON, VANG_KHONG_PHEP
    }
}