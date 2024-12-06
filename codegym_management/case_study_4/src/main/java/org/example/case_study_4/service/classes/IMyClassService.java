package org.example.case_study_4.service.classes;

import org.example.case_study_4.model.Classes;
import java.util.List;
import java.util.Optional;

public interface IMyClassService {
    List<Classes> findAllClasses();
    Optional<Classes> findClassById(Integer id);
    Classes saveClass(Classes classes);
    void deleteClass(Integer id);
    List<Classes> findClassesByCourseId(Integer courseId);
    List<Classes> findClassesByEmployeeId(Integer employeeId);
}