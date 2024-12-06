package org.example.case_study_4.repository.classes;

import org.example.case_study_4.model.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClassesRepository extends JpaRepository<Classes, Integer> {
//    List<Classes> findAllByIsDeleteFalse();

    Page<Classes> findAllByIsDeleteFalse(Pageable pageable);
}

