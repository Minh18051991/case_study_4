package org.example.case_study_4.repository.classes;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes, Integer> {
    List<Classes> findByEmployee(Employee employee);
}
