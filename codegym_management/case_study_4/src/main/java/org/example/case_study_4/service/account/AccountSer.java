package org.example.case_study_4.service.account;

import org.example.case_study_4.model.Account;
import org.example.case_study_4.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountSer implements IAccountSer{
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account getAccount(String userName) {
        return accountRepository.findByUsername(userName);
    }
}
