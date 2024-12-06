package org.example.case_study_4.service.classes;

import org.example.case_study_4.model.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClassService {
    //    List<Classes> findAll();
    Page<Classes> findAll(Pageable pageable);

    Classes findByIdClass(Integer id);

    Classes saveClass(Classes classes);

    void deleteByIdClass(Integer id);

    Classes findById(Integer classId);

    List<Classes> findAllClasses();
}
