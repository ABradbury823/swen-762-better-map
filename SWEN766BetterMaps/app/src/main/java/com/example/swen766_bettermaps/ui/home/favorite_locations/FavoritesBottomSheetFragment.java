package com.example.swen766_bettermaps.ui.home.favorite_locations;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.swen766_bettermaps.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;

public class FavoritesBottomSheetFragment extends BottomSheetDialogFragment {
    // TODO: add comments to all favorites files
    private RecyclerView recyclerView;
    private List<String> favoriteLocations;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for the bottom sheet
        View view = inflater.inflate(R.layout.favorite_locations_menu, container, false);

        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data (replace with actual favorite locations)
        favoriteLocations = new ArrayList<>();
        favoriteLocations.add("Tiger Statue");
        favoriteLocations.add("Golisano Hall");
        favoriteLocations.add("Cantina & Grille at Global Village");

        // Set the adapter for the RecyclerView
        FavoritesAdapter adapter = new FavoritesAdapter(favoriteLocations);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

