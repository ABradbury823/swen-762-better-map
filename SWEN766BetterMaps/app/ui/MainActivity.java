package com.example.swen766_bettermaps.ui;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

import com.example.swen766_bettermaps.R;
import com.example.swen766_bettermaps.ui.home.favorite_locations.FavoritesBottomSheetFragment;
import com.example.swen766_bettermaps.ui.home.route_filter.RouteFilterPopupWindow;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.swen766_bettermaps.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration;
        if (getIntent().getParcelableExtra("RIT_USER") == null) {
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_account, R.id.navigation_home)
                    .build();
        } else {
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.accountFragment, R.id.navigation_home)
                    .build();
        }
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        // button to open filters
        ImageButton openFilterButton = findViewById(R.id.openRouteFilterButton);
        openFilterButton.setOnClickListener(this::openRouteFilters);

        // button to open favorites
        ImageButton openFavoritesButton = findViewById(R.id.openFavoritesButton);
        openFavoritesButton.setOnClickListener(this::openFavorites);
    }

    // opens the route filters popup menu
    public void openRouteFilters(View view) {
        RouteFilterPopupWindow routeFilterPopupWindow =
                new RouteFilterPopupWindow(MainActivity.this);
        routeFilterPopupWindow.show(view);
    }

    // opens the favorites menu
    public void openFavorites(View view) {
        FavoritesBottomSheetFragment favorites = new FavoritesBottomSheetFragment();
        favorites.show(getSupportFragmentManager(), favorites.getTag());
    }
}