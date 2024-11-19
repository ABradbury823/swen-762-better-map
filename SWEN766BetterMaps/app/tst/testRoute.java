package app.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import app.src.Route;

public class testRoute {

    @Test
    public void testGetRoute() {
        Route route = new Route();
        String result = route.getRoute();
        assertEquals("Path: A -> B", result); // Expected real value
    }

    @Test
    public void testGetAllFeedback() {
        Route route = new Route();
        String result = route.getAllFeedback();
        assertEquals("Feedback: Great route!", result); // Expected real value
    }

    @Test
    public void testViewData() {
        Route route = new Route();
        String result = route.viewData();
        assertEquals("Admin Data View", result); // Expected real value
    }

    @Test
    public void testAddMaintenanceUpdate() {
        Route route = new Route();
        String result = route.addMaintenanceUpdate();
        assertEquals("Maintenance update added successfully", result); // Expected real value
    }

    @Test
    public void testGetTravelTimes() {
        Route route = new Route();
        String result = route.getTravelTimes();
        assertEquals("10 mins", result); // Expected real value
    }
}
