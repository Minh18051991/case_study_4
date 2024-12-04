package org.example.case_study_4.service.account;

import jakarta.transaction.Transactional;
import org.example.case_study_4.model.Account;
import org.example.case_study_4.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account findAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Transactional
    public void updateAccount(String username, String newPass) {
        Account account = accountRepository.findByUsername(username);

        if (account != null) {
            BCryptPasswordEncoder newPasswordEncoder = new BCryptPasswordEncoder();
            newPass = newPasswordEncoder.encode(newPass);
            account.setPassword(newPass);
            accountRepository.save(account);
        }
    }

    @Transactional
    public  void saveAccount(Account account) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public boolean checkAccount(String username) {
        boolean result = false;
        for (Account account : accountRepository.findAll()) {
            if (account.getUsername().equals(username)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
