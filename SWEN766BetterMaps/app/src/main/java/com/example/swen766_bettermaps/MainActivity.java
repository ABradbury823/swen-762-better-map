package com.example.swen766_bettermaps;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.swen766_bettermaps.ui.home.route_filter.RouteFilterPopupWindow;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.swen766_bettermaps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity /*implements OnMapReadyCallback*/ {

    private ActivityMainBinding binding;
//    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_account, R.id.navigation_home)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

//        Button loginButton = (Button) findViewById(R.id.navigation_account);
//        loginButton.setOnClickListener(view -> {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        });

//        binding.navView.setOnNavigationItemSelectedListener(item -> {
//            if (item.getItemId() == R.id.navigation_account) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                return true;
//            }
//            return false;
//        });


        // button to open filters
        ImageButton openFilterButton = findViewById(R.id.openRouteFilterButton);
        openFilterButton.setOnClickListener(this::openRouteFilters);
    }

    /*@Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker and move the camera
        LatLng RIT = new LatLng(43.0839295, -77.680005); // Example coordinates (Sydney)
        mMap.addMarker(new MarkerOptions().position(RIT).title("Golisano Hall on RIT Campus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RIT, 10)); // Zoom level 10
    }*/


    // opens the route filters popup menu
    public void openRouteFilters(View view) {
        RouteFilterPopupWindow routeFilterPopupWindow =
                new RouteFilterPopupWindow(MainActivity.this);
        routeFilterPopupWindow.show(view);
    }
}