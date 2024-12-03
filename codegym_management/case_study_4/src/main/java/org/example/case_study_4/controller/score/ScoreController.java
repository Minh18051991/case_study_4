package org.example.case_study_4.controller.score;

import org.example.case_study_4.service.score.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private IScoreService scoreService;

    @GetMapping("list_score")
    public String listScore(Model model) {
        int studentId = 1;
        model.addAttribute("listScore", scoreService.findAllScoreByStudentId(studentId));
        return "score/list_score";
    }
}
