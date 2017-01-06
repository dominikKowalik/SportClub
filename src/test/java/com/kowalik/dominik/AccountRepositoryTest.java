package com.kowalik.dominik;

import com.kowalik.dominik.dao.AccountRepository;
import com.kowalik.dominik.model.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

	@Autowired
	AccountRepository accountDao;

	@Test
	public void findByLoginTest() {
		Account account = accountDao.findByLogin("aaa");
		Assert.assertNotNull(account);
	}
}
