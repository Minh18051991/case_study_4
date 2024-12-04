package org.example.case_study_4.service.lesson;

import org.example.case_study_4.dto.lessonDto.ResponseLessonDTO;
import org.example.case_study_4.model.Lesson;
import org.example.case_study_4.repository.lesson.ILessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService implements ILessonService {
    @Autowired
    private ILessonRepository lessonRepository;

    @Override
    public List<ResponseLessonDTO> findLessonByStudentIdAndModuleId(Integer moduleId) {
        return lessonRepository.findLessonByStudentIdAndModuleId(moduleId);
    }

    @Override
    public Lesson findLessonByLessonId(Integer lessonId) {
        return lessonRepository.findById(lessonId).orElse(null);
    }

}
