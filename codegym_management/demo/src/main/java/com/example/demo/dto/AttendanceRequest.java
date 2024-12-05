package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttendanceRequest {
    private Integer studentId;
    private Integer classId;
    private Integer employeeId;
    private String status;
    private String note;

    // Getters and Setters

}
