package org.example.case_study_4.repository.lesson;

import org.example.case_study_4.dto.lessonDto.ResponseLessonDTO;
import org.example.case_study_4.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ILessonRepository extends JpaRepository<Lesson, Integer> {
    @Query(value = "select l.id as lessonId, l.name as lessonName\n" +
            "from module m \n" +
            "join lesson l on l.module_id = m.id\n" +
            "where m.id = :moduleId and l.is_delete = false and m.is_delete = false;", nativeQuery = true)
    List<ResponseLessonDTO> findLessonByStudentIdAndModuleId(@Param("moduleId") Integer moduleId);
    List<Lesson> findByModuleId(Integer moduleId);

    List<Lesson> findByModuleIdAndIsDeleteFalse(Integer moduleId);
}
