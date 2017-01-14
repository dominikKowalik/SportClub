package com.kowalik.dominik.web;

import com.kowalik.dominik.model.Account;
import com.kowalik.dominik.model.Employee;
import com.kowalik.dominik.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dominik on 2017-01-14.
 */

@RestController
@RequestMapping("trainer")
public class TrainerController {

    @Autowired
    AccountService accountService;

    @GetMapping("{login}")
    public ResponseEntity<Employee> getTrainer(@PathVariable("login") String login){
        Account account = accountService.findAccountByLogin(login);
        Employee employee = account.getEmployee();
        if(employee == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
    }


}
