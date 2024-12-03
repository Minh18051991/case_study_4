package org.example.case_study_4.controller.employee;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.DailyNote;
import org.example.case_study_4.service.classes.IClassService;
import org.example.case_study_4.service.daily_note.IDailyNoteService;
import org.example.case_study_4.service.employeeService.IEmployeeService;
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

    @GetMapping("/{employeeId}")
    public String getClassesByEmployee(@PathVariable String employeeId, Model model) {
        Integer empId;
        try {
            empId = Integer.valueOf(employeeId);
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Giá trị ID giảng viên không hợp lệ.");
            return "employee/employee";
        }
        var classes = employeeService.getClassesByEmployee(empId);
        if (classes.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy lớp học cho giảng viên này.");
            return "employee/employee";
        }
        model.addAttribute("classes", classes);
        model.addAttribute("employeeId", empId);
        return "employee/employee";
    }

    @GetMapping("/class/{classId}/diary")
    public String getDiary(@PathVariable Integer classId, Model model) {
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

    @PostMapping("/class/{classId}/diary")
    public String updateDiary(@PathVariable Integer classId, @RequestParam String content, Model model) {
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

    @GetMapping("/class/{classId}/diary/create")
    public String createDiaryForm(@PathVariable Integer classId, Model model) {
        Optional<Classes> currentClass = Optional.ofNullable(classService.getClassById(classId));
        if (!currentClass.isPresent()) {
            model.addAttribute("message", "Lớp học không tồn tại.");
            return "employee/diary";
        }
        model.addAttribute("class", currentClass.get());
        return "employee/create-diary";
    }

    @PostMapping("/class/{classId}/diary/create")
    public String createDiary(@PathVariable Integer classId, @RequestParam String content, Model model) {
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

    @GetMapping("/class/{classId}/diary/edit/{noteId}")
    public String editDiaryForm(@PathVariable Integer classId, @PathVariable Integer noteId, Model model) {
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


    @PostMapping("/class/{classId}/diary/edit/{noteId}")
    public String updateDiary(@PathVariable Integer classId, @PathVariable Integer noteId, @RequestParam String content, Model model) {
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
    @GetMapping("/class/{classId}/diary/delete/{noteId}")
    public String deleteDiary(@PathVariable Integer classId, @PathVariable Integer noteId, Model model) {
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
