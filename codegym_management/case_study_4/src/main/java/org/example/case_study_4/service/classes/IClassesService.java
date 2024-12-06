package org.example.case_study_4.service.classes;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Employee;

import java.util.List;

public interface IClassesService {
    List<Classes> findAllByEmployee(Employee employee);
}
