package org.example.case_study_4.service.progress;

import org.example.case_study_4.dto.progressDto.ProgressDTO;
import org.example.case_study_4.model.Progress;

import java.util.List;

public interface IProgressService {
    List<ProgressDTO> findAllByLessonIdAndStudentId(int studentId, int lessonId);
    void saveProgress(Progress progress);
    Progress findByIdAndActivityIdAndStudentId(int id, int activityId, int studentId);
}
