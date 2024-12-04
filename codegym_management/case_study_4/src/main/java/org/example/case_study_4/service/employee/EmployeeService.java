package org.example.case_study_4.service.employee;

import jakarta.transaction.Transactional;
import org.example.case_study_4.model.Employee;
import org.example.case_study_4.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService  implements IEmployeeService  {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Page<Employee> findAllAndSearch(String name, Pageable pageable) {


        if (name!= null && !name.isEmpty()) {
            return employeeRepository.findAllByNameContaining(name, pageable);
        }else {
            return employeeRepository.findAll(pageable);
        }
    }

    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }
}
