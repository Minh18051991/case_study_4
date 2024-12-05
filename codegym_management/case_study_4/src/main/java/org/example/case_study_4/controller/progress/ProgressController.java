package org.example.case_study_4.controller.progress;

import org.example.case_study_4.dto.progressDto.ProgressDTO;
import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.account.IAccountSer;
import org.example.case_study_4.service.lesson.ILessonService;
import org.example.case_study_4.service.progress.IProgressService;
import org.example.case_study_4.service.studentL.IStudentSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/progress")
public class ProgressController {
    @Autowired
    private IProgressService progressService;
    @Autowired
    private ILessonService lessonService;
    @Autowired
    private IAccountSer accountSer;
    @Autowired
    private IStudentSer studentSer;

    @GetMapping("/detail")
    public String detailProgress(@RequestParam("id") Integer lessonId, Model model, Principal principal) {
        String username = principal.getName();

        Account account = accountSer.getAccount(username);
        Student student = studentSer.findByAccount(account);

        Integer studentId = student.getId();


        List<ProgressDTO> list = progressService.findAllByLessonIdAndStudentId(studentId, lessonId);
        model.addAttribute("progressList", list);
        model.addAttribute("lesson", lessonService.findLessonByLessonId(lessonId));
        return "progress/detail";
    }


}
