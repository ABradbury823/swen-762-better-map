package com.example.swen766_bettermaps.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.swen766_bettermaps.Location;
import com.example.swen766_bettermaps.Route;
import com.example.swen766_bettermaps.ui.home.favorite_locations.FavoritesBottomSheetFragment;
import com.example.swen766_bettermaps.ui.home.route_filter.RouteFilterPopupWindow;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.swen766_bettermaps.R;
import com.example.swen766_bettermaps.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private FragmentHomeBinding binding;
    private GoogleMap mMap;

    private Marker lastPlacedMarker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the map
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // button to open filters
        ImageButton openFilterButton = view.findViewById(R.id.openRouteFilterButton);
        openFilterButton.setOnClickListener(this::openRouteFilters);

        // button to open favorites
        ImageButton openFavoritesButton = view.findViewById(R.id.openFavoritesButton);
        openFavoritesButton.setOnClickListener(this::openFavorites);
    }

    // opens the route filters popup menu
    public void openRouteFilters(View view) {
        RouteFilterPopupWindow routeFilterPopupWindow =
                new RouteFilterPopupWindow(this.getContext());
        routeFilterPopupWindow.show(view);
    }

    // opens the favorites menu
    public void openFavorites(View view) {
        FavoritesBottomSheetFragment favorites = new FavoritesBottomSheetFragment();
        favorites.show(getActivity().getSupportFragmentManager(), favorites.getTag());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng TigerStatue = new LatLng(43.084180,-77.675593);
        mMap.addMarker(new MarkerOptions().position(TigerStatue).title("Tiger Statue"));

        LatLng Golisano = new LatLng(43.083872,-77.67986);
        mMap.addMarker(new MarkerOptions().position(Golisano).title("Golisano Building"));

        LatLngBounds RITBounds = new LatLngBounds(
                new LatLng(43.078414, -77.687614), // Southwest corner
                new LatLng(43.092335, -77.665870)  // Northeast corner
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TigerStatue, 15));

        // Lock the map to the defined bounds
        mMap.setLatLngBoundsForCameraTarget(RITBounds);

        // Optionally, set the minimum zoom level to prevent zooming out too much
        mMap.setMinZoomPreference(14.0f);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        Route myRoute = new Route(
                new Location("Tiger Statue", TigerStatue),
                new Location("Golisano", Golisano)
        );

        Location imaging = new Location(
                "Chester F. Carlson Center for Imaging Science",
                "54 Lomb Memorial Dr"
        );
        myRoute.addStop(imaging);

        mMap.clear();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mMap.clear();

                if(lastPlacedMarker != null) {
                    Marker startMarker = mMap.addMarker(new MarkerOptions().position(lastPlacedMarker.getPosition()).title(lastPlacedMarker.getTitle()));
                    lastPlacedMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Sum Marker"));

                    Route pinRoute = new Route(new Location("A", startMarker.getPosition()), new Location("B", lastPlacedMarker.getPosition()));
                    pinRoute.drawRoute(mMap);
                } else {
                    lastPlacedMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Sum Marker"));
                }
            }
        });
    }

}