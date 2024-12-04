package org.example.case_study_4.controller.activity;

import org.example.case_study_4.model.Activity;

import org.example.case_study_4.model.Lesson;
import org.example.case_study_4.service.activity.IActivityService;
import org.example.case_study_4.service.category_activity.CategoryActivityService;
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
    @Autowired
    private CategoryActivityService categoryActivityService;

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
        model.addAttribute("categories", categoryActivityService.findAllNotDeleted());
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("module", lessonService.findById(lessonId).get().getModule());
        return "activity/create";
    }

    @PostMapping("/create/{lessonId}")
    public String createActivity(@PathVariable Integer lessonId, @ModelAttribute Activity activity, RedirectAttributes redirectAttributes) {
        Lesson lesson = lessonService.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson Id:" + lessonId));
        activity.setLesson(lesson);
        activityService.save(activity);
        redirectAttributes.addFlashAttribute("message", "Activity created successfully!");
        return "redirect:/lessons/view/" + lessonId;
    }

    @GetMapping("/edit/{id}")
    public String editActivityForm(@PathVariable Integer id, Model model) {
        Activity activity = activityService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid activity Id:" + id));
        model.addAttribute("activity", activity);
        model.addAttribute("categories", categoryActivityService.findAllNotDeleted());
        return "activity/edit";
    }

    @PostMapping("/edit/{id}")
    public String editActivity(@PathVariable Integer id, @ModelAttribute Activity activity, RedirectAttributes redirectAttributes) {
        Activity existingActivity = activityService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid activity Id:" + id));
        existingActivity.setName(activity.getName());
        existingActivity.setCategory(activity.getCategory());
        activityService.save(existingActivity);
        redirectAttributes.addFlashAttribute("message", "Activity updated successfully!");
        return "redirect:/lessons/view/" + existingActivity.getLesson().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteActivity(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Activity activity = activityService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid activity Id:" + id));
        activity.setIsDelete(true);
        activityService.save(activity);
        redirectAttributes.addFlashAttribute("message", "Activity deleted successfully!");
        return "redirect:/lessons/view/" + activity.getLesson().getId();
    }
}