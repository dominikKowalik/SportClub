package com.kowalik.dominik.service;

import com.kowalik.dominik.dao.AccountRepository;
import com.kowalik.dominik.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dominik on 2017-01-14.
 */

@Service("accountService")
@Transactional
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account findAccountByLogin(String login){
        return accountRepository.findByLogin(login);
    }

}
