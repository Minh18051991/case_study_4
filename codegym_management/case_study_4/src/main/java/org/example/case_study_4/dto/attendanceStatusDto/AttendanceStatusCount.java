package org.example.case_study_4.dto.attendanceStatusDto;

public class AttendanceStatusCount {
    private int absenceWithPermission;
    private int absenceWithoutPermission;
    private int late;

    public AttendanceStatusCount() {
    }

    public AttendanceStatusCount(int absenceWithPermission, int absenceWithoutPermission, int late) {
        this.absenceWithPermission = absenceWithPermission;
        this.absenceWithoutPermission = absenceWithoutPermission;
        this.late = late;
    }

    public int getAbsenceWithPermission() {
        return absenceWithPermission;
    }

    public void setAbsenceWithPermission(int absenceWithPermission) {
        this.absenceWithPermission = absenceWithPermission;
    }

    public int getAbsenceWithoutPermission() {
        return absenceWithoutPermission;
    }

    public void setAbsenceWithoutPermission(int absenceWithoutPermission) {
        this.absenceWithoutPermission = absenceWithoutPermission;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }
}
