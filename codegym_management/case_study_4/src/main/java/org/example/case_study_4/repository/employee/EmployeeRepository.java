package org.example.case_study_4.repository.employee;

import org.example.case_study_4.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
