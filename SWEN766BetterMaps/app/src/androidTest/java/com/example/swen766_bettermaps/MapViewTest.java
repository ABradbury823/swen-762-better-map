package com.example.swen766_bettermaps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.not;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class MapViewTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityScenario = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Checks if the map view is visible on launch and when navigating to the home fragment
     * using the navbar.
     */
    @Test
    public void checkMapView() {
        onView(withId(R.id.map)).check(matches(isDisplayed()));
        onView(withId(R.id.navigation_account)).perform(click());
        onView(withId(R.id.map)).check(matches(not(isDisplayed())));
        onView(withId(R.id.navigation_home)).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }
}
