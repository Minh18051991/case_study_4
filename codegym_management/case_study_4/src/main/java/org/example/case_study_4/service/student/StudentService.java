package org.example.case_study_4.service.student;

import org.example.case_study_4.model.Student;
import org.example.case_study_4.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudent {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudentsByClassId(Integer classId) {

        List<Student> students = studentRepository.findByClassEntity_Id(classId);

        if (students == null || students.isEmpty()) {
            return students;
        }

        return students;
    }
}
