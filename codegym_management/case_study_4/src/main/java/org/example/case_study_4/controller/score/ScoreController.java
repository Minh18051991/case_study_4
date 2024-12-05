package org.example.case_study_4.controller.score;

import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.account.IAccountSer;
import org.example.case_study_4.service.score.IScoreService;
import org.example.case_study_4.service.studentL.IStudentSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private IScoreService scoreService;
    @Autowired
    private IAccountSer accountSer;
    @Autowired
    private IStudentSer studentSer;

    @GetMapping("list_score")
    public String listScore(Model model, Principal principal) {
        String username = principal.getName();

        Account account = accountSer.getAccount(username);
        Student student = studentSer.findByAccount(account);

        Integer studentId = student.getId();


        model.addAttribute("listScore", scoreService.findAllScoreByStudentId(studentId));
        return "score/list_score";
    }
}
