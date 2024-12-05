package org.example.case_study_4.service.course;

import org.example.case_study_4.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> findAll();
    List<Course> findAllActive();
    Optional<Course> findById(Integer id);
    Course save(Course course);
    Course update(Course course);
    void softDelete(Integer id);
    boolean existsById(Integer id);
}