package com.kowalik.dominik.controllerTest;

import com.kowalik.dominik.dao.AccountRepository;
import com.kowalik.dominik.model.Account;
import com.kowalik.dominik.web.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by dominik on 2017-01-06.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {


    MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new LoginController()).build();
    }

    @Test
    public void loginTest() throws Exception {
        Account account = accountRepository.findByLogin("stefan123");
        this.mockMvc.perform(get("/login/stefan123").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.name").value("dyrektor"));
    }


}
