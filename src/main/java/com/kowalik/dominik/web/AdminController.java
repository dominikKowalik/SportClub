package com.kowalik.dominik.web;

import com.kowalik.dominik.dao.ClubRepository;
import com.kowalik.dominik.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dominik on 2017-01-07.
 */

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    ClubRepository clubRepository;

    //only one club in db
    @GetMapping( value = "/club", produces = "application/json")
    public ResponseEntity<Club> fetchClub(){
        Club club = clubRepository.findOne(1);
        if(club == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(club, HttpStatus.OK);
    }

}
