package org.example.case_study_4.repository.student;

import org.example.case_study_4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByClassEntity_Id(Integer classId);
}
