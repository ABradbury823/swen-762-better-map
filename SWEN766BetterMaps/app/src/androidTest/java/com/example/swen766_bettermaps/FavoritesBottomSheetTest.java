package com.example.swen766_bettermaps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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
        onView(withText("Tiger Statue")).check(matches(isDisplayed()));
        onView(withText("Golisano Hall")).check(matches(isDisplayed()));
        onView(withText("Cantina & Grille at Global Village")).check(matches(isDisplayed()));
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
}
