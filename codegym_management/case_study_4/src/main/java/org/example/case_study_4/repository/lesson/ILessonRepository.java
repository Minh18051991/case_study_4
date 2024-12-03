package org.example.case_study_4.repository.lesson;

import org.example.case_study_4.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findByModuleId(Integer moduleId);

    List<Lesson> findByModuleIdAndIsDeleteFalse(Integer moduleId);
}
