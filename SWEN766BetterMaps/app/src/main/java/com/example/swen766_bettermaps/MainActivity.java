package com.example.swen766_bettermaps;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.swen766_bettermaps.ui.home.route_filter.RouteFilterPopupWindow;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.maps.model.LatLngBounds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.swen766_bettermaps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityMainBinding binding;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Initialize the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // button to open filters
        ImageButton openFilterButton = findViewById(R.id.openRouteFilterButton);
        openFilterButton.setOnClickListener(this::openRouteFilters);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker and move the camera

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
//
        myRoute.drawRoute(mMap);

//        MapUtils.drawRoute(
//            mMap,
//            myRoute.getOrigin().getCoordinates(),
//            myRoute.getDestination().getCoordinates(),
//            BuildConfig.MAPS_API_KEY
//        );
    }

    // opens the route filters popup menu
    public void openRouteFilters(View view) {
        RouteFilterPopupWindow routeFilterPopupWindow =
                new RouteFilterPopupWindow(MainActivity.this);
        routeFilterPopupWindow.show(view);
    }
}