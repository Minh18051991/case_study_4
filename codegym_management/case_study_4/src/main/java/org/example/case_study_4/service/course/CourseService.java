package org.example.case_study_4.service.course;

import org.example.case_study_4.model.Course;
import org.example.case_study_4.model.MyModule;
import org.example.case_study_4.model.Lesson;
import org.example.case_study_4.model.Activity;
import org.example.case_study_4.repository.course.ICourseRepository;
import org.example.case_study_4.repository.my_module.IModuleRepository;
import org.example.case_study_4.repository.lesson.ILessonRepository;
import org.example.case_study_4.repository.activity.IActivityRepository;
import org.example.case_study_4.dto.CourseCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IModuleRepository moduleRepository;

    @Autowired
    private ILessonRepository lessonRepository;

    @Autowired
    private IActivityRepository activityRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return courseRepository.existsById(id);
    }

    @Override
    @Transactional
    public Course createFullCourse(CourseCreationDTO courseCreationDTO) {
        Course course = courseRepository.save(courseCreationDTO.getCourse());

        for (MyModule module : courseCreationDTO.getModules()) {
            module.setCourse(course);
            MyModule savedModule = moduleRepository.save(module);

            for (Lesson lesson : courseCreationDTO.getLessons()) {
                if (lesson.getModule() != null && lesson.getModule().getId().equals(module.getId())) {
                    lesson.setModule(savedModule);
                    Lesson savedLesson = lessonRepository.save(lesson);

                    for (Activity activity : courseCreationDTO.getActivities()) {
                        if (activity.getLesson() != null && activity.getLesson().getId().equals(lesson.getId())) {
                            activity.setLesson(savedLesson);
                            activityRepository.save(activity);
                        }
                    }
                }
            }
        }

        return course;
    }

    @Override
    @Transactional
    public MyModule addModuleToCourse(Integer courseId, MyModule newModule) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        newModule.setCourse(course);
        return moduleRepository.save(newModule);
    }

    @Override
    @Transactional
    public Lesson addLessonToModule(Integer moduleId, Lesson newLesson) {
        MyModule module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new EntityNotFoundException("Module not found"));
        newLesson.setModule(module);
        return lessonRepository.save(newLesson);
    }

    @Override
    @Transactional
    public Activity addActivityToLesson(Integer lessonId, Activity newActivity) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));
        newActivity.setLesson(lesson);
        return activityRepository.save(newActivity);
    }

    @Override
    @Transactional
    public Course updateCourse(Integer courseId, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        existingCourse.setName(updatedCourse.getName());
        existingCourse.setDuration(updatedCourse.getDuration());
        // Update other fields as necessary
        return courseRepository.save(existingCourse);
    }

    @Override
    @Transactional
    public MyModule updateModule(Integer moduleId, MyModule updatedModule) {
        MyModule existingModule = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new EntityNotFoundException("Module not found"));
        existingModule.setName(updatedModule.getName());
        // Update other fields as necessary
        return moduleRepository.save(existingModule);
    }

    @Override
    @Transactional
    public Lesson updateLesson(Integer lessonId, Lesson updatedLesson) {
        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));
        existingLesson.setName(updatedLesson.getName());
        // Update other fields as necessary
        return lessonRepository.save(existingLesson);
    }

    @Override
    @Transactional
    public Activity updateActivity(Integer activityId, Activity updatedActivity) {
        Activity existingActivity = activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found"));
        existingActivity.setName(updatedActivity.getName());
        // Update other fields as necessary
        return activityRepository.save(existingActivity);
    }
}