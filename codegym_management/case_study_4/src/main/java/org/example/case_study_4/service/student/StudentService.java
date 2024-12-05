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

    // Lấy danh sách sinh viên theo classId
    @Override
    public List<Student> getStudentsByClassId(Integer classId) {
        // Trả về danh sách sinh viên có classId tương ứng
        return studentRepository.findByClassEntity_Id(classId);
    }

    // Find by studentId: Tìm một sinh viên theo ID
    @Override
    public Optional<Student> findById(Integer studentId) {
        // Trả về một Optional<Student> thay vì List
        return studentRepository.findById(studentId);
    }

    @Override
    public List<Student> findByClassEntity_Id(Integer classId) {
        return studentRepository.findByClassEntity_Id(classId);
    }
}
