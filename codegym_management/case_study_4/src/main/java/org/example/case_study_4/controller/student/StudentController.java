package org.example.case_study_4.controller.student;

import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.classes.IClassService;
import org.example.case_study_4.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final IClassService classesService;

    @Autowired
    public StudentController(StudentService studentService, IClassService classesService) {
        this.studentService = studentService;
        this.classesService = classesService;
    }

    @GetMapping("class/{classId}/employee/{employeeId}")
    public String viewStudents(@PathVariable Integer classId, @PathVariable Integer employeeId, Model model) {
        if (!classesService.isTeacherAssignedToClass(employeeId, classId)) {
            model.addAttribute("message", "Giảng viên không phụ trách lớp học này.");
            return "student/error";
        }
        List<Student> students = studentService.getStudentsByClassId(classId);
        if (students == null || students.isEmpty()) {
            model.addAttribute("message", "Không có học viên nào trong lớp học này.");
            return "student/error";
        }
        model.addAttribute("students", students);
        model.addAttribute("classId", classId);
        model.addAttribute("employeeId", employeeId);
        return "student/list";
    }
}
