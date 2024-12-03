package com.example.swen766_bettermaps.ui.tunnel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swen766_bettermaps.R;

import java.util.ArrayList;
import java.util.List;

import com.example.swen766_bettermaps.R;
import com.example.swen766_bettermaps.tunnelData.DormsideTunnels;
import com.example.swen766_bettermaps.tunnelData.DormsideTunnels.*;

public class TunnelFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tunnel, container, false);
        DormsideTunnels map = new DormsideTunnels();


        Spinner startLocationSpinner = root.findViewById(R.id.start_location_spinner);
        Spinner endLocationSpinner = root.findViewById(R.id.end_location_spinner);
        Button showRouteButton = root.findViewById(R.id.show_route_button);
        TextView routeInstructions = root.findViewById(R.id.route_instructions);

        List<String> buildingTitles = new ArrayList<>();
        buildingTitles.add("Grace Watson Hall");
        buildingTitles.add("Frances Baker Hall");
        buildingTitles.add("Kate Gleason");
        buildingTitles.add("Eugene Colby Hall");
        buildingTitles.add("Sol Heumann");
        buildingTitles.add("Carleton Gibson Hall");
        buildingTitles.add("Peter Peterson Hall");
        buildingTitles.add("Residence Hall D");
        buildingTitles.add("Lyndon Baines Johnson");
        buildingTitles.add("Helen Fish");
        buildingTitles.add("Ritchies Game Room (Carleton Gibson)");
        buildingTitles.add("Hettie L. Shumway Dining Commons");
        buildingTitles.add("Residence Hall A");
        buildingTitles.add("Residence Hall B");
        buildingTitles.add("Residence Hall C");
        buildingTitles.add("Mark Ellingson");
        buildingTitles.add("Douglas Sprague Perry");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, buildingTitles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        startLocationSpinner.setAdapter(adapter);
        endLocationSpinner.setAdapter(adapter);

        showRouteButton.setOnClickListener(view -> {
            String startLocation = (String) startLocationSpinner.getSelectedItem();
            String endLocation = (String) endLocationSpinner.getSelectedItem();

            if (startLocation != null && endLocation != null) {
                try {
                    String route = map.buildRoute(startLocation, endLocation);
                    routeInstructions.setText(route);
                } catch (Exception e) {
                    routeInstructions.setText("Error generating route: " + e.getMessage());
                }
            } else {
                routeInstructions.setText("Please select both start and end locations.");
            }
        });

        return root;
    }
}
