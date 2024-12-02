package org.example.case_study_4.controller.course;

import org.example.case_study_4.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;
    @Autowired
    private IModuleService moduleService;
    @Autowired
    private ILessonService lessonService;
    @Autowired
    private IActivityService activityService;

    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "course/list";
    }

    @GetMapping("/create")
    public String createCourseForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("module", new Module());
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("activity", new Activity());
        return "course/create";
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute Course course,
                               @RequestParam List<String> moduleNames,
                               @RequestParam List<String> lessonNames,
                               @RequestParam List<String> activityNames) {
        courseService.save(course);
        
        for (int i = 0; i < moduleNames.size(); i++) {
            Module module = new Module();
            module.setName(moduleNames.get(i));
            module.setCourse(course);
            moduleService.save(module);

            Lesson lesson = new Lesson();
            lesson.setName(lessonNames.get(i));
            lesson.setModule(module);
            lessonService.save(lesson);

            Activity activity = new Activity();
            activity.setName(activityNames.get(i));
            activity.setLesson(lesson);
            activityService.save(activity);
        }

        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable Integer id, Model model) {
        Course course = courseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        model.addAttribute("course", course);
        return "course/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCourse(@PathVariable Integer id, @ModelAttribute Course course) {
        course.setId(id);
        courseService.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/view/{id}")
    public String viewCourse(@PathVariable Integer id, Model model) {
        Course course = courseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        model.addAttribute("course", course);
        return "course/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }
}