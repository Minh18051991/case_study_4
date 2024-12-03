package org.example.case_study_4.controller.progress;

import org.example.case_study_4.model.Progress;
import org.example.case_study_4.service.progress.IProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/progress")
public class ProgressController {
    @Autowired
    private IProgressService progressService;

    @GetMapping("/detail")
    public String detailProgress(@RequestParam("id") Integer lessonId, Model model) {
        int studentId = 1;
        model.addAttribute("progressList", progressService.findAllByLessonIdAndStudentId(studentId, lessonId));
        return "progress/detail";
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam Integer progressId, @RequestParam Integer studentId,
                               @RequestParam Integer activityId, @RequestParam(value = "status", required = false) Boolean status, Model model) {

        System.out.println("progressId = " + progressId);
        Progress progress = progressService.findByIdAndActivityIdAndStudentId(progressId, studentId, activityId);

        if (progress != null) {
            progress.setStatus(status != null && status);
            progressService.saveProgress(progress);
        }

        return "redirect:/progress/detail?id=" + progressId;
    }
}
