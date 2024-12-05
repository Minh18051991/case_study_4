package org.example.case_study_4.controller.progress;

import org.example.case_study_4.dto.progressDto.UpdateProgress;
import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Activity;
import org.example.case_study_4.model.Progress;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.account.IAccountSer;
import org.example.case_study_4.service.activi.IActivitySer;
import org.example.case_study_4.service.studentL.IStudentSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.case_study_4.service.progress.IProgressService;

import java.security.Principal;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/progress")
public class RestProgressController {
    @Autowired
    private IProgressService progressService;
    @Autowired
    private IStudentSer studentSer;
    @Autowired
    private IActivitySer activitySer;
    @Autowired
    private IAccountSer accountSer;

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateProgressStatus(@RequestBody UpdateProgress updateProgress, Principal principal) {
        String username = principal.getName();

        Account account = accountSer.getAccount(username);
        Student student = studentSer.findByAccount(account);

        Integer studentId = student.getId();
        Progress progress = progressService.findByActivityIdAndStudentId(updateProgress.getActivityId(), studentId);

        if (progress != null) {
            progress.setStatus(updateProgress.getStatus());
            progressService.saveProgress(progress);
            return new ResponseEntity<>(progress, HttpStatus.OK);
        } else {
            Progress newProgress = new Progress();
            newProgress.setStatus(true);
            Student student1 = studentSer.findById(studentId);
            newProgress.setStudent(student1);
            Activity activity = activitySer.findById(updateProgress.getActivityId());
            newProgress.setActivity(activity);
            progressService.saveProgress(newProgress);
            return new ResponseEntity<>(newProgress, HttpStatus.OK);
        }
    }

}
