package com.example.swen766_bettermaps;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.swen766_bettermaps.ui.home.route_filter.RouteFilterSettings;

public class RouteFilterSettingsTest {
    // build default route filter settings with builder static class
    @Test
    public void buildDefaultRFS() {
        boolean expectedIsFastest = true;
        boolean expectedIsIndoors = false;

        RouteFilterSettings settings = new RouteFilterSettings.Builder().build();

        assertEquals(expectedIsFastest, settings.getIsFastestRoute());
        assertEquals(expectedIsIndoors, settings.getIsIndoorsOnly());
    }

    // build parameterized settings with chained builder class methods
    @Test
    public void buildParamsRFS() {
        boolean expectedIsFastest = false;
        boolean expectedIsIndoors = true;

        RouteFilterSettings settings = new RouteFilterSettings.Builder()
                .setFastestRoute(expectedIsFastest)
                .setUseIndoorsOnly(expectedIsIndoors)
                .build();

        assertEquals(expectedIsFastest, settings.getIsFastestRoute());
        assertEquals(expectedIsIndoors, settings.getIsIndoorsOnly());
    }
}
