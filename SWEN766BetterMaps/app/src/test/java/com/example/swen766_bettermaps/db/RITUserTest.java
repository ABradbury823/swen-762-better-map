package com.example.swen766_bettermaps.db;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.swen766_bettermaps.data.Administrator;
import com.example.swen766_bettermaps.data.RITFaculty;
import com.example.swen766_bettermaps.data.RITStudent;
import com.example.swen766_bettermaps.data.RITUser;

public class RITUserTest {

    private RITUser student;
    private RITUser faculty;
    private RITUser admin;

    @Before
    public void setUp() {
        student = new RITStudent("123", "John Doe", "john.doe@rit.edu");
        faculty = new RITFaculty("456", "Dr. Smith", "dr.smith@rit.edu");
        admin = new Administrator("789", "Admin User", "admin@rit.edu");
    }

    @Test
    public void testGetRITId() {
        assertEquals("123", student.getRIT_id());
        assertEquals("456", faculty.getRIT_id());
        assertEquals("789", admin.getRIT_id());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John Doe", student.getFirst_name());
        assertEquals("Dr. Smith", faculty.getFirst_name());
        assertEquals("Admin User", admin.getFirst_name());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.doe@rit.edu", student.getEmail());
        assertEquals("dr.smith@rit.edu", faculty.getEmail());
        assertEquals("admin@rit.edu", admin.getEmail());
    }
}
