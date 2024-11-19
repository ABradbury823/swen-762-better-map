package app.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import app.src.Office;

public class testOffice {

    @Test
    public void testAddOfficeHours() {
        Office office = new Office();
        String result = office.addOfficeHours();
        assertEquals("Office hours added successfully", result); // Expected real value
    }
}
