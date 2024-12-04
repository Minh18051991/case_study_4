package org.example.case_study_4.service.activity;

import org.example.case_study_4.model.Activity;
import org.example.case_study_4.repository.activity.IActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService implements IActivityService {

    @Autowired
    private IActivityRepository activityRepository;

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public Optional<Activity> findById(Integer id) {
        return activityRepository.findById(id);
    }

    @Override
    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void deleteById(Integer id) {
        activityRepository.deleteById(id);
    }

    @Override
    public List<Activity> findByLessonId(Integer lessonId) {
        return activityRepository.findByLessonId(lessonId);
    }
}