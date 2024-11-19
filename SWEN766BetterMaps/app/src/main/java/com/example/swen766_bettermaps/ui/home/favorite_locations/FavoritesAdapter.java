package com.example.swen766_bettermaps.ui.home.favorite_locations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swen766_bettermaps.R;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<String> favoriteLocations;

    public FavoritesAdapter(List<String> favoriteLocations) {
        this.favoriteLocations = favoriteLocations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String location = favoriteLocations.get(position);
        holder.locationName.setText(location);
    }

    @Override
    public int getItemCount() {
        return favoriteLocations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;

        public ViewHolder(View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.location_name);
        }
    }
}
