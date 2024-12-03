package org.example.case_study_4.service.student;

import org.example.case_study_4.model.Student;

import java.util.List;

public interface IStudent {
    List<Student> getStudentsByClassId(Integer classId);
}
