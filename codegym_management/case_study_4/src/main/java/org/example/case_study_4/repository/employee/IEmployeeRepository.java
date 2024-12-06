package org.example.case_study_4.repository.employee;

import org.example.case_study_4.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByIsDeleteFalse();
    Employee findById(int id);
}
