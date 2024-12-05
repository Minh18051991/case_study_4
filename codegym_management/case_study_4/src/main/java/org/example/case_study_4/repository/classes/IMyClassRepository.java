package org.example.case_study_4.repository.classes;

import org.example.case_study_4.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMyClassRepository extends JpaRepository<Classes, Integer> {
    List<Classes> findByCourseId(Integer courseId);
    List<Classes> findByEmployeeId(Integer employeeId);

    @Query("SELECT c FROM Classes c WHERE c.isDelete = false")
    List<Classes> findAllActiveClasses();
}