package com.example.swen766_bettermaps;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.TextView;

import com.example.swen766_bettermaps.ui.home.favorite_locations.FavoritesAdapter;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FavoritesAdapterTest {

    // tests that count of items matches length of favorite locations list
    @Test
    public void testGetItemCount() {
        List<String> favoriteLocations = Arrays.asList("Gosnell Hall", "Student Alumni Union");
        FavoritesAdapter adapter = new FavoritesAdapter(favoriteLocations);

        assertEquals(favoriteLocations.size(), adapter.getItemCount());
    }

    // tests that binding the view holder sets the text view correctly
    @Test
    public void testOnBindViewHolder() {
        List<String> favoriteLocations = Arrays.asList("Gosnell Hall", "Student Alumni Union");
        FavoritesAdapter adapter = new FavoritesAdapter(favoriteLocations);

        // make a mock object for the View
        View mockView = mock(View.class);
        // make a mock object for the Text View
        TextView locationNameTextView = mock(TextView.class);
        // when findById is called by mock view, it should return the location_name Text View
        when(mockView.findViewById(R.id.location_name)).thenReturn(locationNameTextView);

        // Bind data to first item on the list
        FavoritesAdapter.ViewHolder holder = new FavoritesAdapter.ViewHolder(mockView);
        adapter.onBindViewHolder(holder, 0);

        // check that the text view's text is set correctly
        verify(locationNameTextView).setText("Gosnell Hall");
    }
}
