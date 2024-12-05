//package org.example.case_study_4.repository.score;
//
//import org.example.case_study_4.dto.scoreDto.ScoreDTO;
//import org.example.case_study_4.model.Score;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface IScoreRepository extends JpaRepository<Score, Integer> {
//    /*@Query(value = "select sc.theory, sc.practice, m.name as moduleName, s.id as studentId\n" +
//            "\tfrom module m\n" +
//            "\tjoin score sc on m.id = sc.module_id\n" +
//            "\tjoin student s on s.id = sc.student_id\n" +
//            "\twhere s.id = ", nativeQuery = true)
//    List<ScoreDTO> findAllScore();*/
//
//    List<ScoreDTO> findAllScoreByStudentId(Integer studentId);
//}
