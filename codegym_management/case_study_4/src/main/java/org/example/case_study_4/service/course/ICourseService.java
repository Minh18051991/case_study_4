package org.example.case_study_4.service.course;

import org.example.case_study_4.model.Course;
import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> findAll();
    Optional<Course> findById(Integer id);
    Course save(Course course);
    void deleteById(Integer id);
    boolean existsById(Integer id);
}