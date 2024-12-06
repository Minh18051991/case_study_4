package org.example.case_study_4.service.daily_note;

import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.DailyNote;
import org.example.case_study_4.repository.daily_note.DailyNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DailyNoteService implements IDailyNoteService {

    private final DailyNoteRepository dailyNoteRepository;

    @Autowired
    public DailyNoteService(DailyNoteRepository dailyNoteRepository) {
        this.dailyNoteRepository = dailyNoteRepository;
    }

    @Override
    public DailyNote getLatestDailyNoteForClass(Classes classEntity) {
        return dailyNoteRepository.findTopByClassEntityOrderByCreateAtDesc(classEntity);
    }

    @Override
    public void saveDailyNote(DailyNote dailyNote) {
        dailyNoteRepository.save(dailyNote);
    }

    @Override
    public List<DailyNote> getAllDailyNotesForClass(Classes classes) {
        return dailyNoteRepository.findByClassEntityOrderByCreateAtDesc(classes);
    }

    @Override
    public void updateDailyNote(DailyNote dailyNote) {
        dailyNoteRepository.save(dailyNote);
    }

    @Override
    public void deleteDailyNoteById(Integer dailyNoteId) {
        dailyNoteRepository.deleteById(dailyNoteId);

    }

    @Override
    public Optional<DailyNote> getDailyNoteById(Integer noteId) {
        return dailyNoteRepository.findById(noteId);
    }


    @Override
    public List<DailyNote> getDailyNotesForClassInRange(Classes classes, String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return dailyNoteRepository.findByClassEntityAndCreateAtBetween(classes, start, end);
    }


}
