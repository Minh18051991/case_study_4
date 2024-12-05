package org.example.case_study_4.service.studentL;

import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.repository.studentL.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentSer implements IStudentSer {
    @Autowired
    private IStudentRepo studentRepo;
    @Override
    public Student findById(int id) {
        return studentRepo.findById(id).orElse(null);
    }

    @Override
    public Student findByAccount(Account account) {
        return studentRepo.findByAccount(account);
    }

}
