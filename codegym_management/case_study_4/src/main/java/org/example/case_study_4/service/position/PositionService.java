package org.example.case_study_4.service.position;

import org.example.case_study_4.model.EmployeePosition;
import org.example.case_study_4.repository.position.IEmployeePositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService implements IPositionService {
    @Autowired
    private IEmployeePositionRepository positionRepository;

    public List<EmployeePosition> findAll() {
        return positionRepository.findAll();
    }

    public EmployeePosition findById(int id) {
        return positionRepository.findById(id).orElse(null);
    }
}
