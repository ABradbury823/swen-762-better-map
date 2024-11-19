package com.example.swen766_bettermaps;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginFragmentTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityScenario = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void checkLoginNavigation() {
        onView(withId(R.id.navigation_account)).perform(click());
        onView(withId(R.id.sign_in_button)).check(matches(isDisplayed()));
    }

    @Test
    public void checkAccountNavigation() {
        // TODO: check that the login button is not displayed when mock account is logged in
    }
}
