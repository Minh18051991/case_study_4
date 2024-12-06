package org.example.case_study_4.repository.course;

import org.example.case_study_4.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findAllByIsDeleteFalse();
    Course findById(int id);
}
