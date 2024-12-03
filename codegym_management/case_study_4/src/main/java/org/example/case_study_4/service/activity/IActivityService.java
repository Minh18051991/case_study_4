package org.example.case_study_4.service.activity;

import org.example.case_study_4.model.Activity;

import java.util.List;
import java.util.Optional;

public interface IActivityService {
    List<Activity> findAll();
    Optional<Activity> findById(Integer id);
    Activity save(Activity activity);
    void deleteById(Integer id);
    List<Activity> findByLessonId(Integer lessonId);
}