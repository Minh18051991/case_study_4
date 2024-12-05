package org.example.case_study_4.controller.course;

import org.example.case_study_4.model.Course;
import org.example.case_study_4.model.MyModule;
import org.example.case_study_4.service.course.ICourseService;
import org.example.case_study_4.service.my_module.IMyModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @Autowired
    private IMyModuleService moduleService;

    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.findAllActive();
        model.addAttribute("courses", courses);
        return "course/list";
    }

    @GetMapping("/create")
    public String createCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course/create";
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute Course course, RedirectAttributes redirectAttributes) {
        course.setIsDelete(false);
        courseService.save(course);
        redirectAttributes.addFlashAttribute("message", "Course created successfully");
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable Integer id, Model model) {
        Course course = courseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        model.addAttribute("course", course);
        return "course/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCourse(@PathVariable Integer id, @ModelAttribute Course course, RedirectAttributes redirectAttributes) {
        course.setId(id);
        courseService.save(course);
        redirectAttributes.addFlashAttribute("message", "Course updated successfully");
        return "redirect:/courses";
    }

    @GetMapping("/modules/{id}")
    public String viewCourse(@PathVariable Integer id, Model model) {
        Optional<Course> course = courseService.findById(id);
        if (course.isPresent()) {
            model.addAttribute("course", course.get());
            List<MyModule> modules = moduleService.findActiveByCourseId(id);
            model.addAttribute("modules", modules);
            return "course/view";
        } else {
            return "redirect:/courses";
        }
    }

    @GetMapping("/delete/{id}")
    public String softDeleteCourse(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        courseService.softDelete(id);
        redirectAttributes.addFlashAttribute("message", "Course deleted successfully");
        return "redirect:/courses";
    }
}