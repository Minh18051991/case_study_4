package org.example.case_study_4.controller.student;

import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.classes.IClassService;
import org.example.case_study_4.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    // Thay đổi đường dẫn sử dụng @RequestParam thay vì @PathVariable
    @GetMapping("/class")
    public String viewStudents(@RequestParam Integer classId, @RequestParam Integer employeeId, Model model) {
        // Kiểm tra xem giảng viên có phụ trách lớp học này không
        if (!classesService.isTeacherAssignedToClass(employeeId, classId)) {
            model.addAttribute("message", "Giảng viên không phụ trách lớp học này.");
            return "student/error"; // Trả về trang lỗi nếu giảng viên không phụ trách lớp
        }

        // Lấy danh sách học viên trong lớp
        List<Student> students = studentService.getStudentsByClassId(classId);
        if (students == null || students.isEmpty()) {
            model.addAttribute("message", "Không có học viên nào trong lớp học này.");
            return "student/error"; // Trả về trang lỗi nếu không có học viên
        }

        // Thêm dữ liệu vào model để hiển thị trong view
        model.addAttribute("students", students);
        model.addAttribute("classId", classId);
        model.addAttribute("employeeId", employeeId);

        // Trả về trang hiển thị danh sách học viên
        return "student/list";
    }
}
