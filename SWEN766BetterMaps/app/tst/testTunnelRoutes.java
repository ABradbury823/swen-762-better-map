package app.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import app.src.TunnelRoutes;

public class testTunnelRoutes {

    @Test
    public void testGetRoute() {
        TunnelRoutes tunnelRoutes = new TunnelRoutes();
        String result = tunnelRoutes.getRoute("PointA", "PointB");
        assertEquals("Path: PointA -> PointB", result); // Expected real value
    }
}
