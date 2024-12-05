package org.example.case_study_4.service.score;

import org.example.case_study_4.dto.scoreDto.ScoreDTO;
import org.example.case_study_4.repository.score.IScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService implements IScoreService {

    @Autowired
    private IScoreRepository scoreRepository;

    @Override
    public List<ScoreDTO> findAllScoreByStudentId(Integer studentId) {
        return scoreRepository.findAllScoreByStudentId(studentId);
    }
}
