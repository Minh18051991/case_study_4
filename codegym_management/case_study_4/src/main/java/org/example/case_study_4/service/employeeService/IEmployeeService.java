package org.example.case_study_4.service.employeeService;



import org.example.case_study_4.model.Classes;

import java.util.List;

public interface  IEmployeeService  {

    List<Classes> getClassesByEmployee(Integer employeeId);

}