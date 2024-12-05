package org.example.case_study_4.repository.student;

import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student,Integer> {
    Student findByAccount(Account account);
}
