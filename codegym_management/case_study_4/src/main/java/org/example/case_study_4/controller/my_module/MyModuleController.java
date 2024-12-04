package org.example.case_study_4.controller.my_module;

import jakarta.validation.Valid;
import org.example.case_study_4.model.Course;
import org.example.case_study_4.model.MyModule;
import org.example.case_study_4.service.course.ICourseService;
import org.example.case_study_4.service.my_module.IMyModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/modules")
public class MyModuleController {

    @Autowired
    private IMyModuleService moduleService;

    @Autowired
    private ICourseService courseService;

    @GetMapping("/create/{courseId}")
    public String createModuleForm(@PathVariable Integer courseId, Model model) {
        model.addAttribute("module", new MyModule());
        model.addAttribute("courseId", courseId);
        return "module/create";
    }



    @GetMapping("/edit/{moduleId}")
    public String editModuleForm(@PathVariable Integer moduleId, Model model) {
        Optional<MyModule> module = moduleService.findActiveById(moduleId);
        if (module.isPresent()) {
            model.addAttribute("module", module.get());
            return "module/edit";
        } else {
            return "redirect:/courses";
        }
    }
    @PostMapping("/create/{courseId}")
    public String createModule(@PathVariable Integer courseId,
                               @Valid @ModelAttribute("module") MyModule module,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "module/create";
        }

        Course course = courseService.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + courseId));
        module.setCourse(course);

        moduleService.save(module);
        redirectAttributes.addFlashAttribute("successMessage", "Module created successfully!");
        return "redirect:/courses/view/" + courseId;
    }

    @PostMapping("/edit/{moduleId}")
    public String editModule(@PathVariable Integer moduleId, @ModelAttribute MyModule module, RedirectAttributes redirectAttributes) {
        Optional<MyModule> existingModule = moduleService.findActiveById(moduleId);
        if (existingModule.isPresent()) {
            MyModule updatedModule = existingModule.get();
            updatedModule.setName(module.getName());
            // Update other fields as necessary
            moduleService.save(updatedModule);
            redirectAttributes.addFlashAttribute("message", "Module updated successfully");
            return "redirect:/courses/view/" + updatedModule.getCourse().getId();
        } else {
            redirectAttributes.addFlashAttribute("error", "Module not found");
            return "redirect:/courses";
        }
    }

    @GetMapping("/delete/{moduleId}")
    public String deleteModule(@PathVariable Integer moduleId, RedirectAttributes redirectAttributes) {
        Optional<MyModule> module = moduleService.findActiveById(moduleId);
        if (module.isPresent()) {
            Integer courseId = module.get().getCourse().getId();
            moduleService.softDelete(moduleId);
            redirectAttributes.addFlashAttribute("message", "Module deleted successfully");
            return "redirect:/courses/view/" + courseId;
        } else {
            redirectAttributes.addFlashAttribute("error", "Module not found");
            return "redirect:/courses";
        }
    }
}