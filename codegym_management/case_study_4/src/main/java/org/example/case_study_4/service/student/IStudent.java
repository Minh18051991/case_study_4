package org.example.case_study_4.service.student;

import org.example.case_study_4.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudent {
    List<Student> getStudentsByClassId(Integer classId);

    List<Student> findByClassEntity_Id(Integer classId);


    Optional<Student> findById(Integer studentId);
}
