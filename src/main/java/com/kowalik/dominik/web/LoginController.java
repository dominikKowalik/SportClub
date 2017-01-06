package com.kowalik.dominik.web;

import com.kowalik.dominik.dao.AccountRepository;
import com.kowalik.dominik.dao.ClubRepository;
import com.kowalik.dominik.dao.EmployeeRepository;
import com.kowalik.dominik.dao.PositionRepository;
import com.kowalik.dominik.model.Account;
import com.kowalik.dominik.model.Club;
import com.kowalik.dominik.model.Employee;
import com.kowalik.dominik.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Created by dominik on 2016-12-31.
 */

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    Account account;

    @Autowired
    Position position;

    @Autowired
    Club club;

    @Autowired
    Employee employee;


    @GetMapping
    @CrossOrigin(origins = "http://localhost:9000")
    public ResponseEntity<String> login(){
        String string = "{\"role\":\"dyrektor\"}";
        return new ResponseEntity<>(string,HttpStatus.OK);
    }


    @GetMapping("/aaa")
    @CrossOrigin(origins = "http://localhost:9000")
    public ResponseEntity<String> aaa(){

        employee.setPesel("321321");
        employee.setMotherName("Grazyna");
        employee.setFatherName("Stanislaw");
        employee.setEducation("srednie");
        employee.setFirstname("Stefan");
        employee.setLastName("Kowalski");

        account.setPassword("123");
        account.setLogin("stefan123");
        account.setRole("dyrektor");

        club.setName("Victoria");
        club.setDateOfEstablishment(LocalDate.now());

        position.setPositionName("dyrektor");

        employee.setAccount(account);
        employee.setPosition(position);
        employee.setClub(club);

        clubRepository.save(club);
        positionRepository.save(position);
        accountRepository.save(account);

        employeeRepository.save(employee);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
