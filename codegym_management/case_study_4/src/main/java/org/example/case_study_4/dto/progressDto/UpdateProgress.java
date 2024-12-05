package org.example.case_study_4.dto.progressDto;

public class UpdateProgress {
    private int activityId;
    private Boolean status;

    public UpdateProgress() {
    }

    public UpdateProgress(int activityId,Boolean status) {
        this.activityId = activityId;
        this.status = status;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
