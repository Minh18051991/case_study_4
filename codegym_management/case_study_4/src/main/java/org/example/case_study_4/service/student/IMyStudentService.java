package org.example.case_study_4.service.student;

import org.example.case_study_4.model.*;

import java.util.List;
import java.util.Optional;

public interface IMyStudentService {
    List<Classes> getAllClasses();
    List<Student> getStudentsByClassId(Integer classId);
    List<MyModule> getModulesByCourseId(Integer courseId);
    Optional<Course> getCourseByClassId(Integer classId);
    void saveStudentScore(Integer studentId, Integer moduleId, Double theory, Double practice);
    List<Score> getScoresByStudentId(Integer studentId);

    // Thêm các phương thức mới
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Integer id);
    Student saveStudent(Student student);
    Student updateStudent(Student student);
    void deleteStudent(Integer id);
    List<Student> searchStudents(String keyword);
}