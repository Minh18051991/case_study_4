package org.example.case_study_4.controller.progress;

import org.example.case_study_4.dto.progressDto.UpdateProgress;
import org.example.case_study_4.model.Activity;
import org.example.case_study_4.model.Progress;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.activi.IActivitySer;
import org.example.case_study_4.service.studentL.IStudentSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.case_study_4.service.progress.IProgressService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/progress")
public class RestProgressController {
    @Autowired
    private IStudentSer studentSer;
    @Autowired
    private IActivitySer activitySer;
    @Autowired
    private IProgressService progressService;

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateProgressStatus(@RequestBody UpdateProgress updateProgress) {

        System.out.println("--------------------");
        System.out.println("activityId" + updateProgress.getActivityId());

        Progress progress = progressService.findByActivityIdAndStudentId(updateProgress.getActivityId(), updateProgress.getStudentId());
        if (progress != null) {
            progress.setStatus(updateProgress.getStatus());
            progressService.saveProgress(progress);
            return new ResponseEntity<>(progress, HttpStatus.OK);
        } else {
            Progress newProgress = new Progress();
            newProgress.setStatus(updateProgress.getStatus());
            Student student = studentSer.findById(updateProgress.getStudentId());
            if (student == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            newProgress.setStudent(student);
            Activity activity = activitySer.findById(updateProgress.getActivityId());
            if (activity == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            newProgress.setActivity(activity);
            progressService.saveProgress(newProgress);
            return new ResponseEntity<>(newProgress,HttpStatus.OK);

        }
    }

}
