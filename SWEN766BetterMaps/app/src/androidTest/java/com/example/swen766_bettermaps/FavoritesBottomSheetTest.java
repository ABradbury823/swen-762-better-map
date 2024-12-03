package com.example.swen766_bettermaps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FavoritesBottomSheetTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // test that pressing the favorites button opens the favorites menu
    @Test
    public void testShowFavoritesMenu() {
        // simulate button click
        onView(withId(R.id.openFavoritesButton)).perform(click());

        // verify if the favorites menu is shown
        onView(withId(R.id.recycler_view_favorites)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewDisplaysCorrectItems() {

        // open the favorites menu
        onView(withId(R.id.openFavoritesButton)).perform(click());

        // Verify that the RecyclerView contains the correct items
        onView(withText("Golisano Hall")).check(matches(isDisplayed()));
        onView(withText("Tiger Statue")).check(matches(isDisplayed()));
        onView(withText("Midnight Oil")).check(matches(isDisplayed()));
    }

    @Test
    public void testHideFavoritesMenu() {
        // open the favorites menu
        onView(withId(R.id.openFavoritesButton)).perform(click());

        // click outside of favorites menu
        onView(isRoot()).perform(click());

        // check that favorites menu is no longer shown
        onView(withId(R.id.recycler_view_favorites)).check(doesNotExist());
    }

    @Before
    public void setUp() {
        String PREFS_NAME = "favorite_locations";
        // Get the SharedPreferences instance
        Context context = ApplicationProvider.getApplicationContext();
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Clear SharedPreferences before each test to avoid interference between tests
        sharedPreferences.edit().clear().apply();
    }

    // test that opening the favorites menu for the first time loads default values
    @Test
    public void firstOpenLoadsDefaults() {
        // open the filters menu
        onView(withId(R.id.openFavoritesButton)).perform(click());

        // check that checkboxes have default loaded values
        onView(withId(R.id.location_name)).check(doesNotExist());
    }
}
