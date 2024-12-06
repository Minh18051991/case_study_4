package org.example.case_study_4.service.classes;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Employee;
import org.example.case_study_4.repository.classes.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesService implements IClassesService  {

    @Autowired
    private ClassesRepository classesRepository;

    public List<Classes> findAllByEmployee(Employee employee) {
        return  classesRepository.findByEmployee(employee);
    }

}
