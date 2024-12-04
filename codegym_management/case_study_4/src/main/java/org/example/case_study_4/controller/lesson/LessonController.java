package org.example.case_study_4.controller.lesson;

import org.example.case_study_4.service.lesson.ILessonService;
import org.example.case_study_4.service.my_module.IMyModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lesson")
public class LessonController {
    @Autowired
    private ILessonService lessonService;
    @Autowired
    private IMyModuleService myModuleService;

    @GetMapping("/detail")
    public String listLesson(@RequestParam("id") int moduleId, Model model) {
        model.addAttribute("listLesson", lessonService.findLessonByStudentIdAndModuleId(moduleId));
        return "lesson/detail";
    }
}
