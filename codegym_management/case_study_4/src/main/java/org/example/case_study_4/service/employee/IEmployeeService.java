package org.example.case_study_4.service.employee;

import org.example.case_study_4.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.example.case_study_4.model.Classes;

import java.util.List;

public interface  IEmployeeService  {

    List<Classes> getClassesByEmployee(Integer employeeId);

    Page<Employee> findAllAndSearch(String position, Pageable pageable);
    void save(Employee employee);
}
