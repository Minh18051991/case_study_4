    package org.example.case_study_4.controller.classes;


    import org.example.case_study_4.model.Classes;
    import org.example.case_study_4.model.Student;
    import org.example.case_study_4.service.classes.IClassService;
    import org.example.case_study_4.service.course.ICourseService;
    import org.example.case_study_4.service.employee.IEmployeeService;
    import org.example.case_study_4.service.student.IStudentService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    import java.time.LocalDateTime;
    import java.util.List;


    @Controller
    @RequestMapping("/classes")
    public class ClassController {

        @Autowired
        private IClassService classService;

        @Autowired
        private ICourseService courseService;

        @Autowired
        private IEmployeeService employeeService;

        @Autowired
        private IStudentService studentService;

//        @GetMapping("")
//        public String listClasses(Model model) {
//            List<Classes> classes = classService.findAll();
//            model.addAttribute("classes", classes);
//            return "/classes/classes";
//        }

        @GetMapping("")
        public String listClasses(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  Model model) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Classes> classesPage = classService.findAll(pageable);
            model.addAttribute("classesPage", classesPage);
            return "/classes/classes";
        }

        @GetMapping("/create")
        public String createClassForm(Model model) {
            model.addAttribute("classes", new Classes());
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("employees", employeeService.findAll());
            return "classes/create";
        }

        @PostMapping("/create")
        public String createClass(@RequestParam int employeeId, @RequestParam int courseId, Classes classes) {
            classes.setEmployee(employeeService.findById(employeeId));
            classes.setCourse(courseService.findById(courseId));
            classes.setStartDate(LocalDateTime.now());
            classService.saveClass(classes);
            return "redirect:/classes";
        }

        @GetMapping("/edit/{id}")
        public String editClassForm(@PathVariable Integer id, Model model) {
            Classes classes = classService.findByIdClass(id);
            if (classes == null || classes.getIsDelete()) {
                return "redirect:/classes";
            }
            model.addAttribute("classes", classes);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("employees", employeeService.findAll());
            return "classes/edit";
        }

        @PostMapping("/edit")
        public String updateClass(@ModelAttribute("classes") Classes classes) {
            if (classes.getStartDate() == null) {
                Classes existingClass = classService.findByIdClass(classes.getId());
                classes.setStartDate(existingClass.getStartDate());
            }
            classService.saveClass(classes);
            return "redirect:/classes";
        }


        @PostMapping("/delete/{id}")
        public String deleteClass(@PathVariable Integer id, Model model) {
            Pageable pageable = PageRequest.of(0, 1);
            Page<Student> students = studentService.findByClassId(id, pageable);

            if (!students.isEmpty()) {
                model.addAttribute("error", "Không thể xoá lớp học vì vẫn còn học sinh trong lớp.");
                return listClasses(0, 10, model);
            }

            classService.deleteByIdClass(id);
            return "redirect:/classes";
        }

        @GetMapping("/students")
        public String getStudentsByClassId(@RequestParam Integer classId,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           Model model) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Student> studentsPage = studentService.findByClassId(classId, pageable);
            model.addAttribute("studentsPage", studentsPage);
            model.addAttribute("classId", classId);
            return "/classes/list_student";
        }

        @GetMapping("/{id}/add-student")
        public String addStudentForm(@PathVariable Integer id, Model model) {
            System.out.println("ID của lớp: " + id);
            Classes classes = classService.findByIdClass(id);
            if (classes == null || classes.getIsDelete()) {
                return "redirect:/classes";
            }
            model.addAttribute("classes", classes);
            model.addAttribute("students", new Student());
            return "/students/add-student";
        }

        @PostMapping("/{id}/add-student")
        public String addStudentToClass(@PathVariable Integer id,
                                        @ModelAttribute Student student,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        Model model, RedirectAttributes redirectAttributes) {
            Classes classes = classService.findByIdClass(id);
            if (classes == null || classes.getIsDelete()) {
                return "redirect:/classes";
            } if(studentService.existsByEmail(student.getEmail())){
                redirectAttributes.addFlashAttribute("error", "Email đã tồn tại! Vui lòng nhập email khác.");
                return "/classes/list_student";
            }

            student.setClassEntity(classes);
            studentService.saveStudent(student);
            Pageable pageable = PageRequest.of(page, size);
            Page<Student> studentsPage = studentService.findByClassId(id, pageable);
            model.addAttribute("classId", id);
            model.addAttribute("studentsPage", studentsPage);
            return "/classes/list_student";
        }


        @GetMapping("/{classId}/move-student/{studentId}")
        public String showMoveStudentForm(@PathVariable Integer classId,
                                          @PathVariable Integer studentId,
                                          Model model) {
            Classes currentClass = classService.findById(classId);
            Student student = studentService.findById(studentId);
            List<Classes> allClasses = classService.findAllClasses();

            if (currentClass == null || student == null) {
                return "redirect:/classes";
            }

            model.addAttribute("currentClass", currentClass);
            model.addAttribute("student", student);
            model.addAttribute("allClasses", allClasses);
            return "/students/move-student";
        }

        @PostMapping("/{classId}/move-student/{studentId}/move")
        public String moveStudentToAnotherClass(@PathVariable Integer classId,
                                                @PathVariable Integer studentId,
                                                @RequestParam Integer newClassId,
                                                RedirectAttributes redirectAttributes) {
            Classes currentClass = classService.findById(classId);
            Student student = studentService.findById(studentId);
            Classes newClass = classService.findById(newClassId);

            if (currentClass == null || student == null || newClass == null) {
                redirectAttributes.addFlashAttribute("error", "Lớp học hoặc học sinh không hợp lệ!");
                return "redirect:/classes";
            }

            student.setClassEntity(newClass);
            studentService.saveStudent(student);

            redirectAttributes.addFlashAttribute("success", "Chuyển lớp thành công!");

            return "redirect:/classes";
        }
    }