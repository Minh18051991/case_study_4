package org.example.case_study_4.repository.student;

import org.example.case_study_4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMyStudentRepository extends JpaRepository<Student, Integer> {


    @Query("SELECT s.classEntity.id FROM Student s WHERE s.id = :studentId")
    Integer findClassIdByStudentId(@Param("studentId") Integer studentId);

    List<Student> findByClassEntity_Id(Integer classId);
}