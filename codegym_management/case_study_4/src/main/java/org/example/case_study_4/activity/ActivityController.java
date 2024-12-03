package org.example.case_study_4.activity;

import org.example.case_study_4.model.Activity;
import org.example.case_study_4.model.Lesson;
import org.example.case_study_4.service.activity.IActivityService;
import org.example.case_study_4.service.lesson.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private IActivityService activityService;
    @Autowired
    private ILessonService lessonService;

    @GetMapping("/lesson/{lessonId}")
    public String listActivities(@PathVariable Integer lessonId, Model model) {
        List<Activity> activities = activityService.findByLessonId(lessonId);
        model.addAttribute("activities", activities);
        model.addAttribute("lessonId", lessonId);
        return "activity/list";
    }

    @GetMapping("/create/{lessonId}")
    public String createActivityForm(@PathVariable Integer lessonId, Model model) {
        model.addAttribute("activity", new Activity());
        model.addAttribute("lessonId", lessonId);
        return "activity/create";
    }

    @PostMapping("/create/{lessonId}")
    public String createActivity(@PathVariable Integer lessonId, @ModelAttribute Activity activity, RedirectAttributes redirectAttributes) {
        Lesson lesson = lessonService.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson Id:" + lessonId));
        activity.setLesson(lesson);
        activityService.save(activity);
        redirectAttributes.addFlashAttribute("message", "Activity created successfully!");
        return "redirect:/activities/lesson/" + lessonId;
    }

    // Add other CRUD operations for activities
}
