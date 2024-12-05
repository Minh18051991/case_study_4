package com.example.demo.service.classes;



import com.example.demo.model.Classes;
import com.example.demo.model.Student;

import java.util.List;

public interface IClassService {
    List<Student> findStudentsByClass(Integer classId);
    List<Classes> findAll();

    boolean isTeacherAssignedToClass(Integer employeeId, Integer classId);


    Classes getClassById(Integer classId);

    List<Classes> getAllClasses();
}
