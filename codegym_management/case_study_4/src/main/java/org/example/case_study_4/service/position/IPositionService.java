package org.example.case_study_4.service.position;

import org.example.case_study_4.model.EmployeePosition;

import java.util.List;

public interface IPositionService {
    List<EmployeePosition> findAll();
    EmployeePosition findById(int id);
}
