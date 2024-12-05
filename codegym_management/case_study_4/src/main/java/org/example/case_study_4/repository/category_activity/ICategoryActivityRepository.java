package org.example.case_study_4.repository.category_activity;

import org.example.case_study_4.model.CategoryActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryActivityRepository extends JpaRepository<CategoryActivity, Integer> {
    List<CategoryActivity> findAllByIsDeleteFalse();
}

