package org.example.case_study_4.service.account;

import org.example.case_study_4.model.Account;

public interface IAccountService {
    Account findAccountByUsername(String username);
    void updateAccount(String username,String newPass);
    void saveAccount(Account account);
    boolean checkAccount(String username);
}
