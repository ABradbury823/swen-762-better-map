package com.example.swen766_bettermaps.ui.home.route_filter;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.swen766_bettermaps.R;

public class RouteFilterPopupWindow {

    private PopupWindow popupWindow;
    private View popupView;

    // Constructor accepts context for inflating and anchor view for positioning
    public RouteFilterPopupWindow(Context context,View anchorView) {
        // inflate the layout for the filter menu
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.route_filter_menu, null);

        // init PopupWindow
        popupWindow = new PopupWindow(
                popupView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // set up popup window properties
        popupWindow.setFocusable(true); // allow interaction
        popupWindow.setOutsideTouchable(true); // close when outside clicked
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog); // animate

        // handle Apply Filters button
        Button applyButton = popupView.findViewById(R.id.applyRouteFilterButton);
        applyButton.setOnClickListener(view -> {
            // get values from filter options
            CheckBox fastestRouteCheckBox = popupView.findViewById(R.id.fastestRouteCheckBox);
            CheckBox indoorOnlyCheckBox = popupView.findViewById(R.id.indoorsOnlyCheckBox);

            boolean fastestRoute = fastestRouteCheckBox.isChecked();
            boolean indoorOnly = indoorOnlyCheckBox.isChecked();

            RouteFilterSettings settings = new RouteFilterSettings.Builder()
                    .setFastestRoute(fastestRoute)
                    .setUseIndoorsOnly(indoorOnly)
                    .build();

            // apply filters
            applyFilters(settings);

            // dismiss window
            popupWindow.dismiss();
        });

        // handle Close button - close without saving
        Button closeButton = popupView.findViewById(R.id.closeRouteFilterButton);
        closeButton.setOnClickListener(view -> { popupWindow.dismiss(); });
    }

    /**
     *  Display the Popup Window at the given anchor view.
     */
    public void show(View anchorView) {
        // position window below anchor
        popupWindow.showAsDropDown(anchorView, 0, 0);
    }

    /**
     * Apply filters based on given values.
      */
    public void applyFilters(RouteFilterSettings settings) {
        // test with a toast
        Toast.makeText(popupView.getContext(),
                "Applied Route Filters: Fastest Route? = " + settings.getIsFastestRoute() +
                        ", Indoors Only? = " + settings.getIsIndoorsOnly(),
                Toast.LENGTH_SHORT).show();
    }
}
