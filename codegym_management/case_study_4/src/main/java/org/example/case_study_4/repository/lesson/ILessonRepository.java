package org.example.case_study_4.repository.lesson;

import org.example.case_study_4.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILessonRepository extends JpaRepository<Lesson, Integer> {
}
