package com.example.swen766_bettermaps.ui.home.favorite_locations;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.example.swen766_bettermaps.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class FavoritesBottomSheetFragment extends BottomSheetDialogFragment
        implements FavoritesAdapter.OnFavoriteDeleteListener {

    private SharedPreferencesHelper sharedPreferencesHelper;
    private List<String> favoriteLocations;
    private FavoritesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext());

        // Inflate the layout for the bottom sheet
        View view = inflater.inflate(R.layout.favorite_locations_menu, container, false);

        // Initialize the RecyclerView - arrange items in a list
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // add some test elements
//        Set<String> testFavorites = new HashSet<>();
//        testFavorites.add("Golisano Hall");
//        testFavorites.add("Tiger Statue");
//        testFavorites.add("Midnight Oil");
//        sharedPreferencesHelper.saveFavorites(testFavorites);

        // retrieve favorites and make sure they aren't null
        Set<String> favorites = sharedPreferencesHelper.getFavorites();
        favoriteLocations = favorites != null ?
                new ArrayList<>(sharedPreferencesHelper.getFavorites()) :
                new ArrayList<>();

        // Set the adapter for the RecyclerView
        adapter = new FavoritesAdapter(getContext(), favoriteLocations, this::onFavoriteDeleted);
        recyclerView.setAdapter(adapter);

        updateFavoriteList();

        return view;
    }

    private void updateFavoriteList() {
        // Convert the Set to a List (if necessary) for RecyclerView to work with
        List<String> favoriteLocationsList = new ArrayList<>(favoriteLocations);

        // Update the adapter with the new list of favorite locations
        adapter.updateFavorites(favoriteLocationsList);
    }

    // Call this method to add a favorite location
    public void addFavoriteLocation(String location) {
        sharedPreferencesHelper.addFavorite(location);
        favoriteLocations.add(location);  // Update local list
        adapter.updateFavorites(new ArrayList<>(favoriteLocations));  // Notify adapter about the new item
    }

    // Call this method to remove a favorite location
    @Override
    public void onFavoriteDeleted(String location) {
        sharedPreferencesHelper.removeFavorite(location);
        favoriteLocations.remove(location);
        adapter.updateFavorites(new ArrayList<>(favoriteLocations));
    }
}

