package org.example.case_study_4.service.category_activity;

import org.example.case_study_4.model.CategoryActivity;

import java.util.List;
import java.util.Optional;

public interface ICategoryActivityService {
    List<CategoryActivity> findAll();
    List<CategoryActivity> findAllNotDeleted();
    Optional<CategoryActivity> findById(Integer id);
    Optional<CategoryActivity> findByIdAndNotDeleted(Integer id);
    CategoryActivity save(CategoryActivity categoryActivity);
    void deleteById(Integer id);
    void softDeleteById(Integer id);
}