package org.example.case_study_4.controller.course;

import org.example.case_study_4.model.Course;
import org.example.case_study_4.model.MyModule;
import org.example.case_study_4.model.Lesson;
import org.example.case_study_4.model.Activity;
import org.example.case_study_4.service.course.ICourseService;
import org.example.case_study_4.dto.CourseCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        return courseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/full")
    public ResponseEntity<Course> createFullCourse(@RequestBody CourseCreationDTO courseCreationDTO) {
        return ResponseEntity.ok(courseService.createFullCourse(courseCreationDTO));
    }

    @PostMapping("/{courseId}/modules")
    public ResponseEntity<MyModule> addModuleToCourse(@PathVariable Integer courseId, @RequestBody MyModule module) {
        return ResponseEntity.ok(courseService.addModuleToCourse(courseId, module));
    }

    @PostMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<Lesson> addLessonToModule(@PathVariable Integer moduleId, @RequestBody Lesson lesson) {
        return ResponseEntity.ok(courseService.addLessonToModule(moduleId, lesson));
    }

    @PostMapping("/lessons/{lessonId}/activities")
    public ResponseEntity<Activity> addActivityToLesson(@PathVariable Integer lessonId, @RequestBody Activity activity) {
        return ResponseEntity.ok(courseService.addActivityToLesson(lessonId, activity));
    }

    @PutMapping("/modules/{moduleId}")
    public ResponseEntity<MyModule> updateModule(@PathVariable Integer moduleId, @RequestBody MyModule module) {
        return ResponseEntity.ok(courseService.updateModule(moduleId, module));
    }

    @PutMapping("/lessons/{lessonId}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Integer lessonId, @RequestBody Lesson lesson) {
        return ResponseEntity.ok(courseService.updateLesson(lessonId, lesson));
    }

    @PutMapping("/activities/{activityId}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Integer activityId, @RequestBody Activity activity) {
        return ResponseEntity.ok(courseService.updateActivity(activityId, activity));
    }
}