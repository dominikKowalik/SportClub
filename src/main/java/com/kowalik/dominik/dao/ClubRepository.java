package com.kowalik.dominik.dao;

import com.kowalik.dominik.model.Club;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dominik on 2016-12-22.
 */


@Repository
public interface ClubRepository extends CrudRepository<Club,Integer> {
}
