package org.example.case_study_4.repository.account_role;

import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer> {
    List<AccountRole> findByAccount(Account account);
}
