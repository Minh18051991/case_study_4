package org.example.case_study_4.repository.activity;

import org.example.case_study_4.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IActivityRepository extends JpaRepository<Activity, Integer> {
    List<Activity> findByLessonId(Integer lessonId);

    List<Activity> findByLessonIdAndIsDeleteFalse(Integer lessonId);
}
