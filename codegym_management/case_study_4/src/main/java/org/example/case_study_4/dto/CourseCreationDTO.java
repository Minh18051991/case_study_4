package org.example.case_study_4.dto;

import org.example.case_study_4.model.Course;
import org.example.case_study_4.model.MyModule;
import org.example.case_study_4.model.Lesson;
import org.example.case_study_4.model.Activity;

import java.util.List;

public class CourseCreationDTO {
    private Course course;
    private List<MyModule> modules;
    private List<Lesson> lessons;
    private List<Activity> activities;

    // Constructors
    public CourseCreationDTO() {}

    // Getters and Setters
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<MyModule> getModules() {
        return modules;
    }

    public void setModules(List<MyModule> modules) {
        this.modules = modules;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}