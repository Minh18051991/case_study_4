package org.example.case_study_4.service.classes;

import jakarta.transaction.Transactional;
import org.example.case_study_4.model.Classes;
import org.example.case_study_4.repository.classes.IClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService implements IClassService {

    @Autowired
    private IClassesRepository classesRepository;

//    @Override
//    public List<Classes> findAll() {
//        return classesRepository.findAllByIsDeleteFalse();
//    }

    @Override
    public Page<Classes> findAll(Pageable pageable) {
        return classesRepository.findAllByIsDeleteFalse(pageable);
    }

    @Override
    public Classes findByIdClass(Integer id) {
        return classesRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Classes saveClass(Classes classes) {
        return classesRepository.save(classes);
    }

    @Transactional
    @Override
    public void deleteByIdClass(Integer id) {
        Classes classes = classesRepository.findById(id).orElse(null);
        if (classes != null) {
            classes.setIsDelete(true);
            classesRepository.save(classes);
        }
    }

    @Override
    public Classes findById(Integer classId) {
        return classesRepository.findById(classId).orElse(null);
    }

    @Override
    public List<Classes> findAllClasses() {
        return classesRepository.findAll();
    }
}
