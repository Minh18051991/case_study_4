package org.example.case_study_4.service.classes;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.repository.classes.IMyClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyClassService implements IMyClassService {

    @Autowired
    private IMyClassRepository myClassRepository;

    @Override
    public List<Classes> findAllClasses() {
        return myClassRepository.findAll();
    }

    @Override
    public Optional<Classes> findClassById(Integer id) {
        return myClassRepository.findById(id);
    }

    @Override
    public Classes saveClass(Classes classes) {
        return myClassRepository.save(classes);
    }

    @Override
    public void deleteClass(Integer id) {
        Optional<Classes> classes = myClassRepository.findById(id);
        if (classes.isPresent()) {
            classes.get().setIsDelete(true);
            myClassRepository.save(classes.get());
        }
    }

    @Override
    public List<Classes> findClassesByCourseId(Integer courseId) {
        return myClassRepository.findByCourseId(courseId);
    }

    @Override
    public List<Classes> findClassesByEmployeeId(Integer employeeId) {
        return myClassRepository.findByEmployeeId(employeeId);
    }
}