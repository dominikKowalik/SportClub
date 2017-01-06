package com.kowalik.dominik.service;

import com.kowalik.dominik.dao.AccountRepository;
import com.kowalik.dominik.model.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by dominik on 2017-01-02.
 */

@Service("customUserDetailsService")
@Scope("prototype")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByLogin(username);
        if(Objects.equals(null, account)){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        System.out.println(account.toString());
        return new User(account.getLogin(),account.getPassword(), true, true, true, true,
                AuthorityUtils.createAuthorityList(account.getRole()));
    }
}
