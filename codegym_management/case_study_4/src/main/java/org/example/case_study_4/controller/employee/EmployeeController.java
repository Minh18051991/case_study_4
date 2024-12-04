package org.example.case_study_4.controller.employee;

import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Employee;
import org.example.case_study_4.model.EmployeePosition;
import org.example.case_study_4.service.account.IAccountService;
import org.example.case_study_4.service.employee.IEmployeeService;
import org.example.case_study_4.service.position.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employees")

public class EmployeeController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPositionService positionService;

    @GetMapping("")
    public String getAllBlogs(@RequestParam(required = false, defaultValue = "0") int page,
                              @RequestParam(required = false, defaultValue = "") String searchName,
                              Model model) {
        Sort sort = Sort.by(Sort.Direction.DESC, "position").and(Sort.by(Sort.Direction.DESC, "name"));
        Pageable pageable = PageRequest.of(page, 2, sort);
        Page<Employee> employees = employeeService.findAllAndSearch( searchName,  pageable);
        model.addAttribute("employees", employees);
        return "employee/list";

    }

    @GetMapping("/add-form")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("positions",positionService.findAll() );
        return "employee/addForm";
    }

    @PostMapping("/create")
    public String createEmployee(@RequestParam String username,@RequestParam String password,@RequestParam int positionId, Employee employee , Model model) {
        EmployeePosition position = positionService.findById(positionId);
        employee.setPosition(position);

        if (!accountService.checkAccount(username)) {
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(password);
            accountService.saveAccount(account);
            employee.setAccount(account);
            employeeService.save(employee);
            return "redirect:/employees";
        }else {
            model.addAttribute("employee", employee);
            model.addAttribute("positions",positionService.findAll() );
            model.addAttribute("error", "Tên tài khoản đã tồn tại!");
            return "employee/addForm";
        }




    }
}
