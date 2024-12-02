package org.example.case_study_4.controller.employee;

import org.example.case_study_4.service.employeeService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeViewController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeViewController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{employeeId}")
    public String getClassesByEmployee(@PathVariable String employeeId, Model model) {
        Integer empId;
        try {
            empId = Integer.valueOf(employeeId);
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Giá trị ID giảng viên không hợp lệ.");
            return "employee/employee";
        }
        var classes = employeeService.getClassesByEmployee(empId);
        if (classes == null || classes.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy lớp học cho giảng viên này.");
            return "employee/employee";
        }
        model.addAttribute("classes", classes);
        model.addAttribute("employeeId", empId);
        return "employee/employee";
    }
}
