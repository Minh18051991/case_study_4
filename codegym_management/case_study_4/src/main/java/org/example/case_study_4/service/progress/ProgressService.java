package org.example.case_study_4.service.progress;

import org.example.case_study_4.dto.progressDto.ProgressDTO;
import org.example.case_study_4.model.Progress;
import org.example.case_study_4.repository.progress.IProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService implements IProgressService {
    @Autowired
    private IProgressRepository progressRepository;
    @Override
    public List<ProgressDTO> findAllByLessonIdAndStudentId(int studentId, int lessonId) {
        return progressRepository.findAllByLessonIdAndStudentId(studentId, lessonId);
    }

    @Override
    public void saveProgress(Progress progress) {
        progressRepository.save(progress);
    }

    @Override
    public Progress findByIdAndActivityIdAndStudentId(int id, int activityId, int studentId) {
        return progressRepository.findByIdAndActivityIdAndStudentId(id, activityId, studentId);
    }
}
