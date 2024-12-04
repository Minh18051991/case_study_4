package org.example.case_study_4.controller.lesson;

import org.example.case_study_4.model.Lesson;
import org.example.case_study_4.model.MyModule;
import org.example.case_study_4.service.lesson.ILessonService;
import org.example.case_study_4.service.my_module.IMyModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private ILessonService lessonService;

    @Autowired
    private IMyModuleService moduleService;


    @GetMapping("/module/{moduleId}")
    public String viewModuleLessons(@PathVariable Integer moduleId, Model model) {
        MyModule module = moduleService.findById(moduleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid module Id:" + moduleId));
        List<Lesson> lessons = lessonService.findByModuleId(moduleId);
        model.addAttribute("module", module);
        model.addAttribute("lessons", lessons);
        return "lesson/list";
    }

    @GetMapping("/detail")
    public String listLesson(@RequestParam("id") int moduleId, Model model) {
        model.addAttribute("listLesson", lessonService.findLessonByStudentIdAndModuleId(moduleId));
        return "lesson/detail";
    }


    @GetMapping("/create/{moduleId}")
    public String createLessonForm(@PathVariable Integer moduleId, Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("moduleId", moduleId);
        return "lesson/create";
    }

    @PostMapping("/create/{moduleId}")
    public String createLesson(@PathVariable Integer moduleId, @ModelAttribute Lesson lesson, RedirectAttributes redirectAttributes) {
        MyModule module = moduleService.findById(moduleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid module Id:" + moduleId));
        lesson.setModule(module);
        lessonService.save(lesson);
        redirectAttributes.addFlashAttribute("message", "Lesson created successfully!");
        return "redirect:/lessons/module/" + moduleId;
    }

    // Thêm các phương thức khác như edit, delete nếu cần
}