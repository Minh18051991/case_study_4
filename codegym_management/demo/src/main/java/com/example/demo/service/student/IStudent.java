package com.example.demo.service.student;



import com.example.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudent {
    List<Student> getStudentsByClassId(Integer classId);

    List<Student> findByClassEntity_Id(Integer classId);


    Optional<Student> findById(Integer studentId);
}
