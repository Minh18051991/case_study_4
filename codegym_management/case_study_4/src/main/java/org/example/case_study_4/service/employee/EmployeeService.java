package org.example.case_study_4.service.employee;

import jakarta.transaction.Transactional;
import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Employee;
import org.example.case_study_4.repository.classes.ClassesRepository;

import org.example.case_study_4.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Employee> findAllAndSearch(String name, Pageable pageable) {

        if (name != null && !name.isEmpty()) {
            return employeeRepository.findAllByNameContaining(name, pageable);
        } else {
            return employeeRepository.findAll(pageable);
        }
    }
            public List<Classes> getClassesByEmployee (Integer employeeId){
                Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
                if (employeeOpt.isEmpty()) {
                    return List.of();
                }
                List<Classes> classes = classesRepository.findByEmployeeId(employeeId);
                return classes != null ? classes : List.of();
            }

            @Transactional
            public void save (Employee employee){
                employeeRepository.save(employee);
            }
        }
