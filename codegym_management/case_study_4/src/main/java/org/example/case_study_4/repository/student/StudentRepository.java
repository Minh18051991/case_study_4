package org.example.case_study_4.repository.student;

import org.example.case_study_4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.classEntity.id = :classId")
    List<Student> findByClassEntity_Id(@Param("classId") Integer classId);
}
