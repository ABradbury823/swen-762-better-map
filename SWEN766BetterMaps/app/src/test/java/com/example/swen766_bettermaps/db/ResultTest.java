package com.example.swen766_bettermaps.db;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.swen766_bettermaps.data.Result;
import com.example.swen766_bettermaps.data.model.LoggedInUser;

public class ResultTest {

    private LoggedInUser user;
    private Result<LoggedInUser> successResult;
    private Result<LoggedInUser> errorResult;

    @Before
    public void setUp() {
        user = new LoggedInUser("123", "Jane Doe");
        successResult = new Result.Success<>(user);
        errorResult = new Result.Error(new Exception("Login failed"));
    }

    @Test
    public void testSuccessResult() {
        assertTrue(successResult instanceof Result.Success);
        assertEquals(user, ((Result.Success<LoggedInUser>) successResult).getData());
    }

    @Test
    public void testErrorResult() {
        assertTrue(errorResult instanceof Result.Error);
        assertNotNull(((Result.Error) errorResult).getError());
        assertEquals("Login failed", ((Result.Error) errorResult).getError().getMessage());
    }



    @Test
    public void testToStringError() {
        String expected = "Error[exception=java.lang.Exception: Login failed]";
        assertEquals(expected, errorResult.toString());
    }
}
