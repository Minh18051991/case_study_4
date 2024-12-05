package org.example.case_study_4.repository.position;

import org.example.case_study_4.model.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployeePositionRepository extends JpaRepository<EmployeePosition, Integer> {
    EmployeePosition findAllById(int id);

}
