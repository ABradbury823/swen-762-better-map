package com.example.swen766_bettermaps.ui;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.example.swen766_bettermaps.Location;
import com.example.swen766_bettermaps.Route;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class MapUtils {

    public static void drawRoute(GoogleMap map, Route route, String apiKey) {
        String url = getDirectionsUrl(route, apiKey);
        new FetchURL(map).execute(url);
    }

    private static String getDirectionsUrl(Route route, String apiKey) {
        // URL for getting directions between origin and destination
        String waypoints = "";
        if (route.getStops().size() > 0) {
            Iterator<Location> stops = route.getStops().iterator();
            waypoints += stops.next().urlFormat();
            while (stops.hasNext()) {
                waypoints += "|" + stops.next().urlFormat();
            }
        }
        return "https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=" + route.getOrigin().urlFormat() +
                "&destination=" + route.getDestination().urlFormat() +
                "&waypoints=" + waypoints +
                "&mode=" + "walking" +
                "&key=" + apiKey;
    }

    private static class FetchURL extends AsyncTask<String, Void, String> {

        private final GoogleMap mMap;

        public FetchURL(GoogleMap map) {
            this.mMap = map;
        }

        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                URL urlConnection = new URL(url[0]);
                HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                connection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();
                br.close();

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            parseAndDrawPath(result, mMap);
        }

        private void parseAndDrawPath(String jsonData, GoogleMap map) {
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray routes = jsonObject.getJSONArray("routes");
                if (routes.length() > 0) {
                    JSONObject route = routes.getJSONObject(0);
                    JSONObject polyline = route.getJSONObject("overview_polyline");
                    String points = polyline.getString("points");

                    // Decode polyline points and draw polyline on the map
                    List<LatLng> decodedPath = PolyUtil.decode(points);
                    map.addPolyline(new PolylineOptions().addAll(decodedPath).color(Color.BLUE).width(10));
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }
    }
}
