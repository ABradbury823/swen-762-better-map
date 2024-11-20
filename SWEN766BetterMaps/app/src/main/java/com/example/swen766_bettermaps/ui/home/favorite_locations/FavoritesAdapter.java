package com.example.swen766_bettermaps.ui.home.favorite_locations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swen766_bettermaps.R;

import java.util.List;

/**
 * Adapter for the <code>RecycleView</code> that connects favorite location data to view items.
 */
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<String> favoriteLocations;

    public FavoritesAdapter(List<String> favoriteLocations) {
        this.favoriteLocations = favoriteLocations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout for each individual item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the favorite location name from the list
        String location = favoriteLocations.get(position);
        // set the location name in the TextView
        holder.locationName.setText(location);
    }

    @Override
    public int getItemCount() {
        return favoriteLocations.size();
    }

    /**
     * Wrapper class for each item in the list. Holds references to all customizable views.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;

        public ViewHolder(View itemView) {
            super(itemView);
            // initialize the views for each item
            locationName = itemView.findViewById(R.id.location_name);
        }
    }
}
