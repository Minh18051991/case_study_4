package org.example.case_study_4.service.classes;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.repository.classes.ClassesRepository;
import org.example.case_study_4.repository.student.StudentRepository;
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
}
