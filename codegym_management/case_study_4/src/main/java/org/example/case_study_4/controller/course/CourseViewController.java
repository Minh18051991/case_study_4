package org.example.case_study_4.controller.course;

import org.example.case_study_4.model.Course;
import org.example.case_study_4.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseViewController {

    @Autowired
    private ICourseService courseService;

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course/list";
    }

    @GetMapping("/{id}")
    public String viewCourse(@PathVariable Integer id, Model model) {
        courseService.findById(id).ifPresent(course -> model.addAttribute("course", course));
        return "course/view";
    }

    @GetMapping("/create")
    public String createCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course/create";
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable Integer id, Model model) {
        courseService.findById(id).ifPresent(course -> model.addAttribute("course", course));
        return "course/edit";
    }
}