package com.kowalik.dominik.dao;

import com.kowalik.dominik.model.Achievement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dominik on 2016-12-26.
 */
@Repository
public interface AchievementRepository extends CrudRepository<Achievement,Integer> {
}
