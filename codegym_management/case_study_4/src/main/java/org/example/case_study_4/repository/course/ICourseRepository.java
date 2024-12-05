package org.example.case_study_4.repository.course;

import org.example.case_study_4.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByIsDeleteFalse();
}
