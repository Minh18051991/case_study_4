package org.example.case_study_4.service.student;

import org.example.case_study_4.model.*;
import org.example.case_study_4.repository.student.IMyStudentRepository;
import org.example.case_study_4.repository.score.IScoreRepository;
import org.example.case_study_4.service.classes.IMyClassService;
import org.example.case_study_4.service.my_module.IMyModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyStudentService implements IMyStudentService {

    @Autowired
    private IMyStudentRepository myStudentRepository;

    @Autowired
    private IMyClassService myClassService;

    @Autowired
    private IMyModuleService myModuleService;

    @Autowired
    private IScoreRepository scoreRepository;

    @Override
    public List<Classes> getAllClasses() {
        return myClassService.findAllClasses();
    }

    @Override
    public List<Student> getStudentsByClassId(Integer classId) {
        return myStudentRepository.findByClassEntity_Id(classId);
    }

    @Override
    public List<MyModule> getModulesByCourseId(Integer courseId) {
        return myModuleService.findActiveByCourseId(courseId);
    }

    @Override
    public Optional<Course> getCourseByClassId(Integer classId) {
        Optional<Classes> classOptional = myClassService.findClassById(classId);
        return classOptional.map(Classes::getCourse);
    }

    @Override
    public void saveStudentScore(Integer studentId, Integer moduleId, Double theory, Double practice) {
        Score score = scoreRepository.findByStudentIdAndModuleId(studentId, moduleId);
        if (score == null) {
            score = new Score();
            score.setStudent(myStudentRepository.findById(studentId).orElseThrow());
            score.setModule(myModuleService.findActiveById(moduleId).orElseThrow());
        }
        score.setTheory(theory);
        score.setPractice(practice);
        scoreRepository.save(score);
    }

    @Override
    public List<Score> getScoresByStudentId(Integer studentId) {
        return scoreRepository.findByStudentId(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return myStudentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Integer id) {
        return myStudentRepository.findById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return myStudentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return myStudentRepository.save(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        myStudentRepository.deleteById(id);
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        // Implement search logic here
        // This is a placeholder implementation
        return myStudentRepository.findAll().stream()
                .filter(student -> student.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}