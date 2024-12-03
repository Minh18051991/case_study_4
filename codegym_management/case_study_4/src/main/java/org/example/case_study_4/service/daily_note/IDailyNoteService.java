package org.example.case_study_4.service.daily_note;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.DailyNote;

import java.util.List;
import java.util.Optional;

public interface IDailyNoteService {

    DailyNote getLatestDailyNoteForClass(Classes classEntity);

    void saveDailyNote(DailyNote dailyNote);

    List<DailyNote> getAllDailyNotesForClass(Classes classes);

    void updateDailyNote(DailyNote dailyNote);

    void deleteDailyNoteById(Integer dailyNoteId);

    Optional<DailyNote> getDailyNoteById(Integer noteId);

    List<DailyNote> getDailyNotesForClassInRange(Classes classes, String startDate, String endDate);
}
