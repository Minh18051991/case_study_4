package org.example.case_study_4.repository.studentL;

import org.example.case_study_4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepo extends JpaRepository<Student, Integer> {
}
