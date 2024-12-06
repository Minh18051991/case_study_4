package org.example.case_study_4.dto.classes;

import org.example.case_study_4.model.Course;
import org.example.case_study_4.model.Employee;

import java.time.LocalDateTime;

public class ClassesDto {
    private int id;
    private String name;
    private LocalDateTime startDate;
    private Employee employee;
    private Course course;

    public ClassesDto() {
    }

    public ClassesDto(int id, String name, LocalDateTime startDate, Employee employee, Course course) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.employee = employee;
        this.course = course;
    }

    public ClassesDto(String name, int courseId, int employeeId) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
