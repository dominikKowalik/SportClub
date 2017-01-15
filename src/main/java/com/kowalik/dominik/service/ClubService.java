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

    public boolean updateClub(Club club){
        Club club1 = clubRepository.findOne(1);
        if(club1 == null){
            return false;
        }else{
            System.out.println(club.getDescription() + "  " + club.getLogo());
            club1.setDescription(club.getDescription());
            club1.setLogo(club.getLogo());
            clubRepository.save(club1);
            return true;
        }
    }

}
