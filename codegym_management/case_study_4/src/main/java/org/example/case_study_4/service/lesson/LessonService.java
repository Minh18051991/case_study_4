package org.example.case_study_4.service.lesson;

import org.example.case_study_4.dto.lessonDto.ResponseLessonDTO;
import org.example.case_study_4.model.Lesson;
import org.example.case_study_4.repository.lesson.ILessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService implements ILessonService {

    @Autowired
    private ILessonRepository lessonRepository;

    @Override
    public List<ResponseLessonDTO> findLessonByStudentIdAndModuleId(Integer moduleId) {
        return lessonRepository.findLessonByStudentIdAndModuleId(moduleId);
    }

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        return lessonRepository.findById(id);
    }

    @Override
    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteById(Integer id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public List<Lesson> findByModuleId(Integer moduleId) {
        return lessonRepository.findByModuleId(moduleId);
    }
}
