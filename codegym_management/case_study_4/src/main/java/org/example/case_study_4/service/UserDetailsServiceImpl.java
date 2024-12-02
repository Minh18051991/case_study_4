//package org.example.case_study_4.service;
//
//import org.example.case_study_4.model.Account;
//import org.example.case_study_4.model.AccountRole;
//import  org.example.case_study_4.repository.account.AccountRepository;
//import  org.example.case_study_4.repository.account_role.AccountRoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private AccountRepository accountRepository;
//    @Autowired
//    private AccountRoleRepository userRoleResposiotry;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Account account = this.accountRepository.findByUsername(userName);
//        if (account == null) {
//            System.out.println("User not found! " + userName);
//            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
//        }
//        System.out.println("Found User: " + account);
//        // lấy ra các role của user
//        // [ROLE_USER, ROLE_ADMIN,..]
//        List<AccountRole> accountRoles = this.userRoleResposiotry.findByAccount(account);
//
//        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//        if (accountRoles != null) {
//            for (AccountRole accountRole : accountRoles) {
//                // ROLE_USER, ROLE_ADMIN,..
//                GrantedAuthority authority = new SimpleGrantedAuthority(accountRole.getRole().getName());
//                grantList.add(authority);
//            }
//        }
//        UserDetails userDetails = new User(account.getUsername(),
//                account.getPassword(), grantList);
//
//        return userDetails;
//    }
//
//}
