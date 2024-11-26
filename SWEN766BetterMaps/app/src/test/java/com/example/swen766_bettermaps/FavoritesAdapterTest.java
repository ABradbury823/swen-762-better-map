package com.example.swen766_bettermaps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.TextView;

import com.example.swen766_bettermaps.ui.home.favorite_locations.FavoritesAdapter;
import com.example.swen766_bettermaps.ui.home.favorite_locations.FavoritesBottomSheetFragment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavoritesAdapterTest {
    @Mock
    private FavoritesAdapter.OnFavoriteDeleteListener mockListener;  // Mock the listener

    private List<String> favoriteLocations;
    private FavoritesAdapter adapter;

    @Before
    public void setup() {
        // Initialize the list with some locations
        favoriteLocations = new ArrayList<>();
        favoriteLocations.add("Location 1");
        favoriteLocations.add("Location 2");
        favoriteLocations.add("Location 3");

        // Create the adapter with the mock context and listener
        adapter = new FavoritesAdapter(favoriteLocations, mockListener);
    }

    @Test
    public void testDeleteButton_click_shouldCallOnFavoriteDeleted() {
        // Mock the behavior when the delete button is clicked (we'll use Mockito to mock the delete button click)
        FavoritesAdapter.ViewHolder mockViewHolder = mock(FavoritesAdapter.ViewHolder.class);
        when(mockViewHolder.itemView.findViewById(R.id.remove_favorite)).thenReturn(mock(View.class));

        // Trigger the delete button click for the first item (Location 1)
        adapter.onBindViewHolder(mockViewHolder, 0); // Bind to "Location 1"

        // Verify that the listener's onFavoriteDeleted method is called with "Location 1"
        verify(mockListener).onFavoriteDeleted("Location 1");
    }

    // tests that count of items matches length of favorite locations list
    @Test
    public void testGetItemCount() {
        assertEquals(favoriteLocations.size(), adapter.getItemCount());
    }
}
