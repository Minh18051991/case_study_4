package org.example.case_study_4.service.category_activity;

import org.example.case_study_4.model.CategoryActivity;
import org.example.case_study_4.repository.category_activity.ICategoryActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryActivityService implements ICategoryActivityService {

    @Autowired
    private ICategoryActivityRepository categoryActivityRepository;

    @Override
    public List<CategoryActivity> findAll() {
        return categoryActivityRepository.findAll();
    }

    @Override
    public List<CategoryActivity> findAllNotDeleted() {
        return categoryActivityRepository.findAll().stream()
                .filter(category -> !category.getIsDelete())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryActivity> findById(Integer id) {
        return categoryActivityRepository.findById(id);
    }

    @Override
    public Optional<CategoryActivity> findByIdAndNotDeleted(Integer id) {
        return categoryActivityRepository.findById(id)
                .filter(category -> !category.getIsDelete());
    }

    @Override
    public CategoryActivity save(CategoryActivity categoryActivity) {
        return categoryActivityRepository.save(categoryActivity);
    }

    @Override
    public void deleteById(Integer id) {
        categoryActivityRepository.deleteById(id);
    }

    @Override
    public void softDeleteById(Integer id) {
        categoryActivityRepository.findById(id).ifPresent(category -> {
            category.setIsDelete(true);
            categoryActivityRepository.save(category);
        });
    }
}