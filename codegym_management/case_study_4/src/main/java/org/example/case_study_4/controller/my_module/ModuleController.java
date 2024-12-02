package org.example.case_study_4.controller.my_module;

import org.example.case_study_4.my_module_dto.ResponseModuleDto;
import org.example.case_study_4.service.my_module.IMyModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private IMyModuleService moduleService;

    @GetMapping("/list_module")
    public String listModule(Model model) {
        int studentId = 1;
        List<ResponseModuleDto> moduleDtoList = moduleService.findModuleByStudentId(studentId);
        System.out.println("----------debug------------");
        for (int i = 0; i <moduleDtoList.size() ; i++) {
            System.out.println(moduleDtoList.get(i).getModuleName());
        }
        model.addAttribute("listModule", moduleService.findModuleByStudentId(studentId));
        return "/my_module/list_module";
    }
}
