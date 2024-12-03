package org.example.case_study_4.repository.progress;

import org.example.case_study_4.dto.progressDto.ProgressDTO;
import org.example.case_study_4.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProgressRepository extends JpaRepository<Progress, Integer> {
    @Query(value = "SELECT p.id as progressId,  p.activity_id AS activityId, a.name AS activityName, p.status AS progressStatus, p.student_id as studentId\n" +
            "            FROM activity a\n" +
            "            LEFT JOIN progress p ON a.id = p.activity_id AND p.student_id = :studentId\n" +
            "            WHERE a.lesson_id = :lessonId", nativeQuery = true)
    List<ProgressDTO> findAllByLessonIdAndStudentId(@Param("studentId") Integer studentId, @Param("lessonId") Integer lessonId);

    Progress findByIdAndActivityIdAndStudentId(Integer id, Integer activityId, Integer studentId);


}
