package org.example.case_study_4.service.activity;

import org.example.case_study_4.model.Activity;

import java.util.List;
import java.util.Optional;

public interface IActivityService {
    Optional<Activity> findById(Integer id);
    Activity save(Activity activity);
    List<Activity> findByLessonId(Integer lessonId);
}