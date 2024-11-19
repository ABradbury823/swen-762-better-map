import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import app.src.Location;

public class LocationTest {

    @Test
    public void testGetCurrentFavorites() {
        Location location = new Location();
        String result = location.getCurrentFavorites();
        assertEquals("Library, Gym", result); // Expected real value
    }

    @Test
    public void testIsOpen() {
        Location location = new Location();
        Boolean result = location.isOpen();
        assertFalse(result); // Expected real value
    }

    @Test
    public void testGetHours() {
        Location location = new Location();
        String result = location.getHours();
        assertEquals("8:00 AM - 5:00 PM", result); // Expected real value
    }

    @Test
    public void testCheckCampusGroupEvents() {
        Location location = new Location();
        String result = location.checkCampusGroupEvents();
        assertEquals("Study Group at 3 PM", result); // Expected real value
    }
}
