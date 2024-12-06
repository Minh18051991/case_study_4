package org.example.case_study_4.service.student;


import org.example.case_study_4.model.Student;
import org.example.case_study_4.repository.classes.IClassesRepository;
import org.example.case_study_4.repository.student.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private IClassesRepository classesRepository;

//    @Override
//    public List<Student> findByClassId(Integer classesId) {
//        return studentRepository.findByClassId(classesId);
//    }

    @Override
    public Page<Student> findByClassId(Integer classesId, Pageable pageable) {
        return studentRepository.findByClassId(classesId, pageable);
    }
    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

        @Override
        public Student findById(Integer studentId) {
            return studentRepository.findById(studentId).orElse(null);
        }
}
