package org.example.case_study_4.service.lesson;

import org.example.case_study_4.dto.lessonDto.ResponseLessonDTO;
import org.example.case_study_4.model.Lesson;

import java.util.List;

public interface ILessonService {
    List<ResponseLessonDTO> findLessonByStudentIdAndModuleId(Integer moduleId);

    Lesson findLessonByLessonId(Integer lessonId);

}
