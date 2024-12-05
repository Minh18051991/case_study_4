package com.example.demo.service.employee;


import com.example.demo.model.Classes;
import com.example.demo.model.Employee;
import com.example.demo.repository.classes.ClassesRepository;
import com.example.demo.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ClassesRepository classesRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ClassesRepository classesRepository) {
        this.employeeRepository = employeeRepository;
        this.classesRepository = classesRepository;
    }

    public List<Classes> getClassesByEmployee(Integer employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isEmpty()) {
            return List.of();
        }
        List<Classes> classes = classesRepository.findByEmployeeId(employeeId);
        return classes != null ? classes : List.of();
    }
}
