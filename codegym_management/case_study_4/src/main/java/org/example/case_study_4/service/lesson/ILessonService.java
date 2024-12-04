package org.example.case_study_4.service.lesson;

import org.example.case_study_4.model.Lesson;
import org.example.case_study_4.dto.lessonDto.ResponseLessonDTO;

import java.util.List;
import java.util.Optional;

public interface ILessonService {
    List<Lesson> findAll();
    Optional<Lesson> findById(Integer id);
    Lesson save(Lesson lesson);
    void deleteById(Integer id);
    List<Lesson> findByModuleId(Integer moduleId);
    List<ResponseLessonDTO> findLessonByStudentIdAndModuleId(Integer moduleId);

}
