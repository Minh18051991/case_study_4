package org.example.case_study_4.service.course;

import org.example.case_study_4.dto.CourseCreationDTO;
import org.example.case_study_4.model.Activity;
import org.example.case_study_4.model.Course;
import org.example.case_study_4.model.Lesson;
import org.example.case_study_4.model.MyModule;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> findAll();
    Course findById(int id);


    Optional<Course> findById(Integer id);

    Course save(Course course);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Course createFullCourse(CourseCreationDTO courseCreationDTO);

    MyModule addModuleToCourse(Integer courseId, MyModule newModule);

    Lesson addLessonToModule(Integer moduleId, Lesson newLesson);

    Activity addActivityToLesson(Integer lessonId, Activity newActivity);

    Course updateCourse(Integer courseId, Course updatedCourse);

    MyModule updateModule(Integer moduleId, MyModule updatedModule);

    Lesson updateLesson(Integer lessonId, Lesson updatedLesson);

    Activity updateActivity(Integer activityId, Activity updatedActivity);
}
