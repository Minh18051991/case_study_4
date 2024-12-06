package org.example.case_study_4.service.employee;

import org.example.case_study_4.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();

    Employee findById(int employeeId);
}
