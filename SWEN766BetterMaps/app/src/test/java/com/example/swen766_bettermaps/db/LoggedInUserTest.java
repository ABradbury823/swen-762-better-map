package com.example.swen766_bettermaps.db;

import static org.junit.Assert.assertEquals;

import com.example.swen766_bettermaps.data.model.LoggedInUser;

import org.junit.Before;
import org.junit.Test;

public class LoggedInUserTest {

    private LoggedInUser user;

    @Before
    public void setUp() {
        user = new LoggedInUser("123", "Jane Doe");
    }

    @Test
    public void testGetUserId() {
        assertEquals("123", user.getUserId());
    }

    @Test
    public void testGetDisplayName() {
        assertEquals("Jane Doe", user.getDisplayName());
    }
}
