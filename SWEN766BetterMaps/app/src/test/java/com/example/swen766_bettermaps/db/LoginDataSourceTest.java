package com.example.swen766_bettermaps.db;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.swen766_bettermaps.data.LoginDataSource;
import com.example.swen766_bettermaps.data.Result;
import com.example.swen766_bettermaps.data.model.LoggedInUser;

public class LoginDataSourceTest {

    private LoginDataSource dataSource;

    @Before
    public void setUp() {
        dataSource = new LoginDataSource();
    }

    @Test
    public void testLoginSuccess() {
        // Assuming that the login method returns a success result
        Result<LoggedInUser> result = dataSource.login("username", "password");
        assertTrue(result instanceof Result.Success);
        assertNotNull(((Result.Success<LoggedInUser>) result).getData());
    }



    @Test
    public void testLogout() {
        // Ensure that logout doesn't throw any errors (currently empty method)
        dataSource.logout();
    }
}
