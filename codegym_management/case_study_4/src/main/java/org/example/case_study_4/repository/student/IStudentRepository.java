package org.example.case_study_4.repository.student;

import org.example.case_study_4.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Integer> {

        @Query("SELECT s FROM Student s WHERE s.classEntity.id = :classId")
        Page<Student> findByClassId(@Param("classId") Integer classId, Pageable pageable);

    boolean existsByEmail(String email);
}


//    @Query("SELECT s FROM Student s WHERE s.classEntity.id = :classId")
//    List<Student> findByClassId(@Param("classId") Integer classId);
//
//}


