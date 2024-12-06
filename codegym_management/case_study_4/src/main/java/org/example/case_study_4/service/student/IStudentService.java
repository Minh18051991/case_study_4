package org.example.case_study_4.service.student;

import org.example.case_study_4.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {
    //    List<Student> findByClassId(Integer classesId);
    Page<Student> findByClassId(Integer classesId, Pageable pageable);
    void saveStudent(Student student);

    boolean existsByEmail(String email);

    Student findById(Integer studentId);
}
