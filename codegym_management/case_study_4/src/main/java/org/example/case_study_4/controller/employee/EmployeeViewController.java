package org.example.case_study_4.controller.employee;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.DailyNote;
import org.example.case_study_4.service.classes.IClassService;
import org.example.case_study_4.service.daily_note.IDailyNoteService;
import org.example.case_study_4.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeViewController {

    private final IEmployeeService employeeService;
    private final IClassService classService;
    private final IDailyNoteService dailyNoteService;

    @Autowired
    public EmployeeViewController(IEmployeeService employeeService, IClassService classService, IDailyNoteService dailyNoteService) {
        this.employeeService = employeeService;
        this.classService = classService;
        this.dailyNoteService = dailyNoteService;
    }


    @GetMapping("/employee")
    public String getClassesByEmployee(@RequestParam Integer employeeId, Model model) {
        // Gọi service để lấy danh sách lớp học của giảng viên
        var classes = employeeService.getClassesByEmployee(employeeId);

        // Nếu không tìm thấy lớp học cho giảng viên, hiển thị thông báo lỗi
        if (classes.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy lớp học cho giảng viên này.");
            return "employee/employee"; // Trả về trang employee với thông báo lỗi
        }

        // Nếu có lớp học, hiển thị danh sách lớp học
        model.addAttribute("classes", classes);
        model.addAttribute("employeeId", employeeId); // Đưa employeeId vào model để sử dụng trong view

        // Trả về trang employee với danh sách lớp học
        return "employee/employee";
    }


    @GetMapping("/class/diary")
    public String getDiary(@RequestParam Integer classId, Model model) {
        Optional<Classes> currentClass = Optional.ofNullable(classService.getClassById(classId));
        if (!currentClass.isPresent()) {
            model.addAttribute("message", "Lớp học không tồn tại.");
            return "employee/diary";
        }
        List<DailyNote> dailyNotes = dailyNoteService.getAllDailyNotesForClass(currentClass.get());
        model.addAttribute("class", currentClass.get());
        model.addAttribute("dailyNotes", dailyNotes);
        return "employee/diary";
    }

    @PostMapping("/class/diary")
    public String updateDiary(@RequestParam Integer classId, @RequestParam String content, Model model) {
        Optional<Classes> currentClass = Optional.ofNullable(classService.getClassById(classId));
        if (!currentClass.isPresent()) {
            model.addAttribute("message", "Lớp học không tồn tại.");
            return "employee/diary";
        }
        DailyNote newDailyNote = new DailyNote();
        newDailyNote.setContent(content);
        newDailyNote.setCreateAt(LocalDateTime.now());
        newDailyNote.setClassEntity(currentClass.get());
        newDailyNote.setEmployee(currentClass.get().getEmployee());
        dailyNoteService.saveDailyNote(newDailyNote);
        List<DailyNote> dailyNotes = dailyNoteService.getAllDailyNotesForClass(currentClass.get());
        model.addAttribute("class", currentClass.get());
        model.addAttribute("dailyNotes", dailyNotes);
        model.addAttribute("message", "Nhật ký lớp học đã được cập nhật.");
        return "employee/diary";
    }

    @GetMapping("/class/diary/create")
    public String createDiaryForm(@RequestParam Integer classId, Model model) {
        Optional<Classes> currentClass = Optional.ofNullable(classService.getClassById(classId));
        if (!currentClass.isPresent()) {
            model.addAttribute("message", "Lớp học không tồn tại.");
            return "employee/diary";
        }
        model.addAttribute("class", currentClass.get());
        return "employee/create-diary";
    }

    @PostMapping("/class/diary/create")
    public String createDiary(@RequestParam Integer classId, @RequestParam String content, Model model) {
        Optional<Classes> currentClass = Optional.ofNullable(classService.getClassById(classId));

        if (!currentClass.isPresent()) {
            model.addAttribute("message", "Lớp học không tồn tại.");
            return "employee/diary";
        }
        DailyNote newDailyNote = new DailyNote();
        newDailyNote.setContent(content);
        newDailyNote.setCreateAt(LocalDateTime.now());
        newDailyNote.setClassEntity(currentClass.get());
        newDailyNote.setEmployee(currentClass.get().getEmployee());
        dailyNoteService.saveDailyNote(newDailyNote);
        List<DailyNote> dailyNotes = dailyNoteService.getAllDailyNotesForClass(currentClass.get());
        model.addAttribute("class", currentClass.get());
        model.addAttribute("dailyNotes", dailyNotes);
        model.addAttribute("message", "Nhật ký lớp học đã được thêm.");
        return "employee/diary";
    }

    @GetMapping("/class/diary/edit")
    public String editDiaryForm(@RequestParam Integer classId, @RequestParam Integer noteId, Model model) {
        Optional<Classes> currentClass = Optional.ofNullable(classService.getClassById(classId));
        if (!currentClass.isPresent()) {
            model.addAttribute("message", "Lớp học không tồn tại.");
            return "employee/diary";
        }
        Optional<DailyNote> dailyNote = dailyNoteService.getDailyNoteById(noteId);
        if (!dailyNote.isPresent()) {
            model.addAttribute("message", "Nhật ký không tồn tại.");
            return "employee/diary";
        }
        model.addAttribute("class", currentClass.get());
        model.addAttribute("dailyNote", dailyNote.get());
        return "employee/edit-diary";
    }

    @PostMapping("/class/diary/edit")
    public String updateDiary(@RequestParam Integer classId, @RequestParam Integer noteId, @RequestParam String content, Model model) {
        Optional<Classes> currentClass = Optional.ofNullable(classService.getClassById(classId));
        if (!currentClass.isPresent()) {
            model.addAttribute("message", "Lớp học không tồn tại.");
            return "employee/diary";
        }
        Optional<DailyNote> existingNote = dailyNoteService.getDailyNoteById(noteId);
        if (!existingNote.isPresent()) {
            model.addAttribute("message", "Nhật ký không tồn tại.");
            return "employee/diary";
        }
        DailyNote updatedNote = existingNote.get();
        updatedNote.setContent(content);
        updatedNote.setCreateAt(LocalDateTime.now());
        dailyNoteService.saveDailyNote(updatedNote);
        List<DailyNote> dailyNotes = dailyNoteService.getAllDailyNotesForClass(currentClass.get());
        model.addAttribute("class", currentClass.get());
        model.addAttribute("dailyNotes", dailyNotes);
        model.addAttribute("message", "Nhật ký lớp học đã được chỉnh sửa.");
        return "employee/diary";
    }

    // New method to delete a DailyNote
    @GetMapping("/class/diary/delete")
    public String deleteDiary(@RequestParam Integer classId, @RequestParam Integer noteId, Model model) {
        Optional<Classes> currentClass = Optional.ofNullable(classService.getClassById(classId));
        if (!currentClass.isPresent()) {
            model.addAttribute("message", "Lớp học không tồn tại.");
            return "employee/diary";
        }
        Optional<DailyNote> dailyNote = dailyNoteService.getDailyNoteById(noteId);
        if (!dailyNote.isPresent()) {
            model.addAttribute("message", "Nhật ký không tồn tại.");
            return "employee/diary";
        }
        dailyNoteService.deleteDailyNoteById(noteId);
        List<DailyNote> dailyNotes = dailyNoteService.getAllDailyNotesForClass(currentClass.get());
        model.addAttribute("class", currentClass.get());
        model.addAttribute("dailyNotes", dailyNotes);
        model.addAttribute("message", "Nhật ký lớp học đã được xóa.");
        return "employee/diary";
    }
}
