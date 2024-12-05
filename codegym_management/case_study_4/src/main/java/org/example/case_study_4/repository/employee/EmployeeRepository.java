package org.example.case_study_4.repository.employee;

import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Employee;
import org.example.case_study_4.model.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.Position;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByAccount(Account account);

    Page<Employee> findAllByNameContaining(String name, Pageable pageable);
}
