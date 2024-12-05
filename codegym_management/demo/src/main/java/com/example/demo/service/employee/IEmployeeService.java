package com.example.demo.service.employee;




import com.example.demo.model.Classes;

import java.util.List;

public interface  IEmployeeService  {

    List<Classes> getClassesByEmployee(Integer employeeId);

}
