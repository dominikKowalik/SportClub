package com.kowalik.dominik.dao;

import com.kowalik.dominik.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dominik on 2016-12-26.
 */

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {
    Account findByLogin(String login);
}
