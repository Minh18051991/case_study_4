package com.example.demo.service.classes;


import com.example.demo.model.Classes;
import com.example.demo.model.Student;
import com.example.demo.repository.classes.ClassesRepository;
import com.example.demo.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService implements IClassService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassesRepository classRepository;

    @Override
    public List<Student> findStudentsByClass(Integer classId) {
        return studentRepository.findByClassEntity_Id(classId);
    }

    @Override
    public List<Classes> findAll() {
        return classRepository.findAll();
    }


    @Override
    public boolean isTeacherAssignedToClass(Integer employeeId, Integer classId) {

        Classes classEntity = classRepository.findById(classId).orElse(null);
        if (classEntity != null && classEntity.getEmployee() != null) {
            return classEntity.getEmployee().getId().equals(employeeId);
        }
        return false;
    }

    @Override
    public Classes getClassById(Integer classId) {

        return classRepository.findById(classId).orElse(null);
    }

    @Override
    public List<Classes> getAllClasses() {
        return List.of();
    }
}
