package com.example.swen766_bettermaps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocused;
import static androidx.test.espresso.matcher.ViewMatchers.isNotFocused;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RouteFilterPopupTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // test that open route filter button opens route filter menu
    @Test
    public void openRouteFiltersMenu() {
        // open the route filters
        onView(withId(R.id.openRouteFilterButton)).perform(click());

        // check that the menu is displayed
        onView(withId(R.id.routeFilterMenu)).check(matches(isDisplayed()));
    }

    // test that clicking Close button closes route filter menu
    @Test
    public void closeRouteFiltersMenuButton() {
        // open the route filters
        onView(withId(R.id.openRouteFilterButton)).perform(click());

        // click the close button
        onView(withId(R.id.closeRouteFilterButton)).perform(click());

        // check that the menu no longer exists
        onView(withId(R.id.routeFilterMenu)).check(doesNotExist());
    }

    @Test
    public void closeRouteFiltersMenuClickOutside() {
        // open the route filters
        onView(withId(R.id.openRouteFilterButton)).perform(click());

        // click outside the route filter
        onView(isRoot()).perform(click());

        // check that the menu no longer focused (will close automatically)
        onView(withId(R.id.routeFilterMenu)).check(matches(isNotFocused()));
    }


}