package org.example.case_study_4.controller.studen_score_controller;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Course;
import org.example.case_study_4.model.MyModule;
import org.example.case_study_4.model.Score;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.student.IMyStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student-scores")
public class StudentScoreController {

    @Autowired
    private IMyStudentService studentService;

    @GetMapping("/classes")
    public String listClasses(Model model) {
        List<Classes> classes = studentService.getAllClasses();
        model.addAttribute("classes", classes);
        return "student-scores/class-list";
    }

    @GetMapping("/classes/{classId}")
    public String listStudentsInClass(@PathVariable Integer classId, Model model) {
        List<Student> students = studentService.getStudentsByClassId(classId);
        Optional<Course> course = studentService.getCourseByClassId(classId);
        model.addAttribute("students", students);
        course.ifPresent(c -> model.addAttribute("course", c));
        return "student-scores/student-list";
    }

    @GetMapping("/student/{studentId}")
    public String studentScores(@PathVariable Integer studentId, Model model) {
        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isPresent()) {
            List<Score> scores = studentService.getScoresByStudentId(studentId);
            model.addAttribute("student", student.get());
            model.addAttribute("scores", scores);
            return "student-scores/score-list";
        }
        return "redirect:/student-scores/classes";
    }

    @GetMapping("/add-score")
    public String addScoreForm(@RequestParam Integer studentId, @RequestParam Integer courseId, Model model) {
        List<MyModule> modules = studentService.getModulesByCourseId(courseId);
        model.addAttribute("studentId", studentId);
        model.addAttribute("modules", modules);
        return "student-scores/add-score";
    }

    @PostMapping("/save-score")
    public String saveScore(@RequestParam Integer studentId,
                            @RequestParam Integer moduleId,
                            @RequestParam Double theory,
                            @RequestParam Double practice) {
        studentService.saveStudentScore(studentId, moduleId, theory, practice);
        return "redirect:/student-scores/student/" + studentId;
    }
}