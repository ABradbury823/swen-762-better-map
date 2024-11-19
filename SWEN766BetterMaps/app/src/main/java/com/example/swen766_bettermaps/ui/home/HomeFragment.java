package com.example.swen766_bettermaps.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swen766_bettermaps.BuildConfig;
import com.example.swen766_bettermaps.Location;
import com.example.swen766_bettermaps.Route;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.swen766_bettermaps.R;
import com.example.swen766_bettermaps.databinding.FragmentHomeBinding;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private FragmentHomeBinding binding;
    private GoogleMap mMap;

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

        // Initialize the Places SDK
        if (!Places.isInitialized()) {
            Places.initialize(getContext(), BuildConfig.MAPS_API_KEY);
        }

        // Set up the AutocompleteSupportFragment
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);

//        System.out.println(autocompleteFragment);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // Handle the selected place
                Log.i("Place", "Place: " + place.getName() + ", " + place.getLatLng());
            }

            @Override
            public void onError(@NonNull Status status) {
                // Handle the error
                Log.e("Place", "An error occurred: " + status);
            }
        });

        return root;
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

        Location magic = new Location("Magic", "300 Lomb Memorial Dr");
        myRoute.addStop(magic);

        Location imaging = new Location(
                "Chester F. Carlson Center for Imaging Science",
                "54 Lomb Memorial Dr"
        );
        myRoute.addStop(imaging);

        myRoute.drawRoute(mMap);

    }

}