package com.example.swen766_bettermaps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocused;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isNotFocused;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.swen766_bettermaps.ui.home.route_filter.RouteFilterSettings;

import org.junit.Before;
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
    public void closeRouteFiltersMenuCloseButton() {
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

    @Test
    public void closeRouteFiltersMenuApplyButton() {
        // open the route filters
        onView(withId(R.id.openRouteFilterButton)).perform(click());

        // click the close button
        onView(withId(R.id.applyRouteFilterButton)).perform(click());

        // check that the menu no longer exists
        onView(withId(R.id.routeFilterMenu)).check(doesNotExist());
    }

    // vv TEST SAVING SETTINGS vv

    private static final String PREFS_NAME = "RouteFilters";
    private static final String KEY_IS_FASTEST_ROUTE = "isFastestRoute";
    private static final String KEY_IS_INDOORS_ONLY = "isIndoorsOnly";

    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        // Get the SharedPreferences instance
        Context context = ApplicationProvider.getApplicationContext();
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Clear SharedPreferences before each test to avoid interference between tests
        sharedPreferences.edit().clear().apply();
    }

    // test that opening the filter menu for the first time loads default values
    @Test
    public void firstOpenLoadsDefaults() {
        // open the filters menu
        onView(withId(R.id.openRouteFilterButton)).perform(click());

        // check that checkboxes have default loaded values
        onView(withId(R.id.fastestRouteCheckBox)).check(matches(isChecked()));
        onView(withId(R.id.indoorsOnlyCheckBox)).check(matches(isNotChecked()));
    }

    // test that clicking Apply button saves filters on subsequent loads
    @Test
    public void applySavesFilters() {
        // open the filters menu
        onView(withId(R.id.openRouteFilterButton)).perform(click());

        // change the checkboxes from their default values
        onView(withId(R.id.fastestRouteCheckBox)).perform(click());
        onView(withId(R.id.indoorsOnlyCheckBox)).perform(click());

        // apply settings by pressing apply button
        onView(withId(R.id.applyRouteFilterButton)).perform(click());

        // re-open the filters menu
        onView(withId(R.id.openRouteFilterButton)).perform(click());

        // check that checkboxes have new loaded values
        onView(withId(R.id.fastestRouteCheckBox)).check(matches(isNotChecked()));
        onView(withId(R.id.indoorsOnlyCheckBox)).check(matches(isChecked()));
    }

}