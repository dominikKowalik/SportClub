package com.kowalik.dominik.web;

import com.kowalik.dominik.dao.AccountRepository;
import com.kowalik.dominik.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dominik on 2016-12-31.
 */

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping(value = "/{login}")
    public ResponseEntity<String> login(@PathVariable("login") String login){
       Account account =  accountRepository.findByLogin(login);
        if(account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"response\":").append("\"").append(account.getRole()).append("\"}");
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }
}
