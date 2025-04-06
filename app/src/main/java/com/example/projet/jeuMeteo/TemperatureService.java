package com.example.projet.jeuMeteo;

import android.os.AsyncTask;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TemperatureService {

    public interface TemperatureCallback {
        void onResult(double temperature);
        void onError(String errorMessage);
    }

    private static final String API_KEY = "dc754e849acc1ea0e3b01c4a1f066598";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void getTemperatureByCity(String cityName, TemperatureCallback callback) {
        String url = BASE_URL + "?q=" + cityName + "&units=metric&appid=" + API_KEY;
        new FetchTemperatureTask(callback).execute(url);
    }

    public static void getTemperatureByLocation(double lat, double lon, TemperatureCallback callback) {
        String url = BASE_URL + "?lat=" + lat + "&lon=" + lon + "&units=metric&appid=" + API_KEY;
        new FetchTemperatureTask(callback).execute(url);
    }

    private static class FetchTemperatureTask extends AsyncTask<String, Void, Double> {
        private final TemperatureCallback callback;
        private String error = null;

        public FetchTemperatureTask(TemperatureCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Double doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );

                StringBuilder json = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    json.append(line);
                }
                in.close();

                JSONObject root = new JSONObject(json.toString());
                return root.getJSONObject("main").getDouble("temp");

            } catch (Exception e) {
                error = e.getMessage();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Double temp) {
            if (temp != null) {
                callback.onResult(temp);
            } else {
                callback.onError(error != null ? error : "Erreur inconnue");
            }
        }
    }
}
