package org.example.case_study_4.dto.progressDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProgress {
    private int activityId;
    private int studentId;
    private Boolean status;

    public UpdateProgress(int activityId, int studentId, Boolean status) {
        this.activityId = activityId;
        this.studentId = studentId;
        this.status = status;
    }
}
