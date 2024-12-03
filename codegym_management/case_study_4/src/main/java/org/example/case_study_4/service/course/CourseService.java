package org.example.case_study_4.service.course;

import org.example.case_study_4.model.Course;
import org.example.case_study_4.repository.course.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findAllActive() {
        return courseRepository.findByIsDeleteFalse();
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        course.setIsDelete(false);
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course update(Course course) {
        if (!courseRepository.existsById(course.getId())) {
            throw new IllegalArgumentException("Course not found with id: " + course.getId());
        }
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void softDelete(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + id));
        course.setIsDelete(true);
        courseRepository.save(course);
    }

    @Override
    public boolean existsById(Integer id) {
        return courseRepository.existsById(id);
    }
}