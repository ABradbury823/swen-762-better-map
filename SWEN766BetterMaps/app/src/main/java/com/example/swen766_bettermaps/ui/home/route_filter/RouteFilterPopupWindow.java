package com.example.swen766_bettermaps.ui.home.route_filter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.swen766_bettermaps.R;

public class RouteFilterPopupWindow {

    private static final String PREFS_NAME = "RouteFilters";
    private static final String KEY_IS_FASTEST_ROUTE = "isFastestRoute";
    private static final String KEY_IS_INDOORS_ONLY = "isIndoorsOnly";

    private final PopupWindow popupWindow;
    private final View popupView;
    private final Context context;

    private final CheckBox fastestRouteCheckBox;
    private final CheckBox indoorsOnlyCheckBox;

    private RouteFilterSettings previousSettings;

    // Constructor accepts context for inflating
    @SuppressLint("InflateParams")
    public RouteFilterPopupWindow(Context context) {
        this.context = context;

        // inflate the layout for the filter menu
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.popupView = inflater.inflate(R.layout.route_filter_menu, null);

        // init PopupWindow
        this.popupWindow = new PopupWindow(
                popupView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // set up popup window properties
        this.popupWindow.setFocusable(true); // allow interaction
        this.popupWindow.setOutsideTouchable(true); // close when outside clicked
        this.popupWindow.setAnimationStyle(android.R.style.Animation_Dialog); // animate

        this.fastestRouteCheckBox = popupView.findViewById(R.id.fastestRouteCheckBox);
        this.indoorsOnlyCheckBox = popupView.findViewById(R.id.indoorsOnlyCheckBox);

        // handle Apply Filters button
        Button applyButton = popupView.findViewById(R.id.applyRouteFilterButton);
        applyButton.setOnClickListener(view -> {

            // set new settings
            this.previousSettings = new RouteFilterSettings.Builder()
                    .setFastestRoute(this.fastestRouteCheckBox.isChecked())
                    .setUseIndoorsOnly(this.indoorsOnlyCheckBox.isChecked())
                    .build();

            saveFilterSettings();

            debugShowFilters();

            // dismiss window
            popupWindow.dismiss();
        });

        // handle Close button - close without saving
        Button closeButton = popupView.findViewById(R.id.closeRouteFilterButton);
        closeButton.setOnClickListener(view -> {

            debugShowFilters();

            popupWindow.dismiss();
        });

        loadFilterSettings();
    }

    /**
     * Retrieve the current route filter settings.
     * @return An object containing all route filter information.
     */
    public RouteFilterSettings getSettings() {
        return this.previousSettings;
    }

    /**
     *  Display the Popup Window at the given anchor view.
     */
    public void show(View anchorView) {
        // position window below anchor
        popupWindow.showAsDropDown(anchorView, 0, 0);
    }

    /**
     * Save the current filter settings to Shared Preferences.
     */
    private void saveFilterSettings() {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_FASTEST_ROUTE, this.previousSettings.getIsFastestRoute());
        editor.putBoolean(KEY_IS_INDOORS_ONLY, this.previousSettings.getIsIndoorsOnly());
        editor.apply(); // commit changes to shared preferences
    }

    /**
     * Load the previous filter settings from Shared Preferences.
     */
    private void loadFilterSettings() {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean lastIsFastestRoute = sharedPreferences.getBoolean(KEY_IS_FASTEST_ROUTE, true);
        boolean lastIsIndoorsOnly = sharedPreferences.getBoolean(KEY_IS_INDOORS_ONLY, false);

        // set ui elements to previous load
        fastestRouteCheckBox.setChecked(lastIsFastestRoute);
        indoorsOnlyCheckBox.setChecked(lastIsIndoorsOnly);

        previousSettings = new RouteFilterSettings.Builder()
                .setFastestRoute(lastIsFastestRoute)
                .setUseIndoorsOnly(lastIsIndoorsOnly)
                .build();
    }

    /**
     * Show filters in a test toast pop-up.
      */
    private void debugShowFilters() {
        // test with a toast
        Toast.makeText(popupView.getContext(),
                "Route Filters: " + this.previousSettings.toString(),
                Toast.LENGTH_SHORT).show();
    }
}
