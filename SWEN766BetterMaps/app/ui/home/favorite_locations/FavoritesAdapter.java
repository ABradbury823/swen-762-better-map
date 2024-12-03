package com.example.swen766_bettermaps.ui.home.favorite_locations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    private final OnFavoriteDeleteListener deleteListener;

    public interface OnFavoriteDeleteListener {
        void onFavoriteDeleted(String location);
    }

    // Constructor with deleteListener to communicate with the Fragment/Activity
    public FavoritesAdapter(List<String> favoriteLocations, OnFavoriteDeleteListener listener) {
        this.favoriteLocations = favoriteLocations;
        this.deleteListener = listener;
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

        // Set remove button click listener
        holder.removeButton.setOnClickListener(v -> {
            // Notify the fragment or activity to remove the favorite
            if (deleteListener != null) {
                deleteListener.onFavoriteDeleted(location);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteLocations.size();
    }

    // Method to update the list of favorite locations
    public void updateFavorites(List<String> newFavorites) {
        this.favoriteLocations = newFavorites;
        // Notify the adapter that the dataset has changed
        notifyDataSetChanged();  // You can optimize this by using notifyItemInserted() / notifyItemRemoved()
    }

    /**
     * Wrapper class for each item in the list. Holds references to all customizable views.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;
        ImageButton removeButton;

        public ViewHolder(View itemView) {
            super(itemView);
            // initialize the views for each item
            locationName = itemView.findViewById(R.id.location_name);
            removeButton = itemView.findViewById(R.id.remove_favorite);
        }
    }
}
