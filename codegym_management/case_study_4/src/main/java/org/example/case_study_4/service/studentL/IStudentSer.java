package org.example.case_study_4.service.studentL;

import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Student;

public interface IStudentSer {
    Student findById(int id);

    Student findByAccount(Account account);
}
