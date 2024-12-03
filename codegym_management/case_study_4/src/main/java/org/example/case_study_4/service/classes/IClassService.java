package org.example.case_study_4.service.classes;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Student;

import java.util.List;

public interface IClassService {
    List<Student> findStudentsByClass(Integer classId);

    boolean isTeacherAssignedToClass(Integer employeeId, Integer classId);

    Classes getClassById(Integer classId);
}
