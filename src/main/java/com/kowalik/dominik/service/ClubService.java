package com.kowalik.dominik.service;

import com.kowalik.dominik.dao.ClubRepository;
import com.kowalik.dominik.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dominik on 2017-01-09.
 */
@Service("clubService")
@Transactional
public class ClubService {
    @Autowired
    ClubRepository clubRepository;

    public Club getClubById(int id) {
        return clubRepository.findOne(id);
    }

    public Club saveEmployee(Club club) {
        return clubRepository.save(club);
    }
}
