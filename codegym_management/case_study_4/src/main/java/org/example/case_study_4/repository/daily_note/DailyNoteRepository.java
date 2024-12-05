package org.example.case_study_4.repository.daily_note;

import org.example.case_study_4.model.DailyNote;
import org.example.case_study_4.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyNoteRepository extends JpaRepository<DailyNote, Integer> {

    DailyNote findTopByClassEntityOrderByCreateAtDesc(Classes classEntity);

    List<DailyNote> findByClassEntityOrderByCreateAtDesc(Classes classes);

    List<DailyNote> findByClassEntityAndCreateAtBetween(Classes classes, LocalDateTime start, LocalDateTime end);
}
