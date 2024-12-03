package com.example.swen766_bettermaps.db;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.swen766_bettermaps.data.LoginDataSource;
import com.example.swen766_bettermaps.data.LoginRepository;
import com.example.swen766_bettermaps.data.Result;
import com.example.swen766_bettermaps.data.model.LoggedInUser;

public class LoginRepositoryTest {

    private LoginRepository loginRepository;
    private LoginDataSource dataSource;

    @Before
    public void setUp() {
        dataSource = new LoginDataSource();
        loginRepository = LoginRepository.getInstance(dataSource);
    }

    @Test
    public void testLoginSuccess() {
        // Test login with valid credentials
        Result<LoggedInUser> result = loginRepository.login("username", "password");
        assertTrue(result instanceof Result.Success);
        assertTrue(loginRepository.isLoggedIn());
    }


    @Test
    public void testLogout() {
        // Log in first, then log out
        loginRepository.login("username", "password");
        assertTrue(loginRepository.isLoggedIn());
        loginRepository.logout();
        assertFalse(loginRepository.isLoggedIn());
    }
}
