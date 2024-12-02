package org.example.case_study_4.repository.activity;

import org.example.case_study_4.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActivityRepository extends JpaRepository<Activity, Integer> {
}
