package org.example.case_study_4.service.activi;

import org.example.case_study_4.model.Activity;
import org.example.case_study_4.repository.activity.IActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitySer implements IActivitySer{
    @Autowired
    private IActivityRepository activityRepository;
    @Override
    public Activity findById(int id) {
        return activityRepository.findById(id).orElse(null);
    }
}
