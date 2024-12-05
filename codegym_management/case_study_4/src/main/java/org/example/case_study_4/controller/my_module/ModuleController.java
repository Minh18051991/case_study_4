package org.example.case_study_4.controller.my_module;

import org.example.case_study_4.dto.my_module_dto.ResponseModuleDto;
import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.account.IAccountSer;
import org.example.case_study_4.service.my_module.IMyModuleService;
import org.example.case_study_4.service.studentL.IStudentSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private IMyModuleService moduleService;
    @Autowired
    private IAccountSer accountSer;
    @Autowired
    private IStudentSer studentSer;

    @GetMapping("/list_module")
    public String listModule(Model model, Principal principal) {
        String username = principal.getName();

        Account account = accountSer.getAccount(username);
        Student student = studentSer.findByAccount(account);

        Integer studentId = student.getId();

        List<ResponseModuleDto> moduleDtoList = moduleService.findModuleByStudentId(studentId);

        model.addAttribute("listModule", moduleDtoList);
        return "/my_module/list_module";
    }
}
