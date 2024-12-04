package org.example.case_study_4.service.score;

import org.example.case_study_4.dto.scoreDto.ScoreDTO;

import java.util.List;

public interface IScoreService {
    List<ScoreDTO> findAllScoreByStudentId(Integer studentId);
}
