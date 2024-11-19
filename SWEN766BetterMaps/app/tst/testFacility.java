import static org.junit.Assert.assertEquals;

import org.junit.Test;

import app.src.Facility.*;

public class testFacility {
    @Test
    public void testCheckAccessibility() {
        Facility facility = new Facility();
        String result = facility.checkAccesiblity("Library");
        assertEquals("Accessible", result); // Expected real value
    }
}
