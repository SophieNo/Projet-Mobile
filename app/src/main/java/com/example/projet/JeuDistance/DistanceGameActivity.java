package com.example.projet.JeuDistance;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.projet.BDDville.AppDatabase;
import com.example.projet.BDDville.DAO.CityDao;
import com.example.projet.BDDville.Entite.City;
import com.example.projet.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class DistanceGameActivity extends AppCompatActivity {

    private static final String PREF_DISTANCE_BEST = "distance_game_best_score";

    private TextView instructionText;
    private EditText inputDistance;
    private Button submitButton;
    private TextView textVilleTiree;
    private Button buttonRetour;

    private FusedLocationProviderClient fusedLocationClient;

    private double userLat;
    private double userLon;
    private double villeLat;
    private double villeLon;
    private String villeCible = "Paris";
    private double vraieDistance = 0;

    private int score = 0;
    private int bestScore = 0;
    private int essai = 0;

    private MapView mapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";


    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    localiserJoueur();
                } else {
                    Toast.makeText(this, "Permission de localisation refusée", Toast.LENGTH_LONG).show();
                    finish();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_game);

        initMap(savedInstanceState);

        instructionText = findViewById(R.id.text_instruction_distance);
        inputDistance = findViewById(R.id.input_distance);
        submitButton = findViewById(R.id.button_submit_distance);
        textVilleTiree = findViewById(R.id.text_ville_distance);
        buttonRetour = findViewById(R.id.button_retour_distance);

        submitButton.setEnabled(false);

        buttonRetour.setOnClickListener(v -> finish());

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SharedPreferences prefs = getSharedPreferences("distance_prefs", MODE_PRIVATE);
        bestScore = prefs.getInt(PREF_DISTANCE_BEST, 0);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            localiserJoueur();
        }

        chargerVilleAleatoire();

        submitButton.setOnClickListener(v -> verifierReponse());


    }

    private void initMap(Bundle savedInstanceState){
        mapView = findViewById(R.id.mapView);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(googleMap -> {
            googleMap.clear();

            LatLng villeLatLng = new LatLng(villeLat, villeLon);
            LatLng userLatLng = new LatLng(userLat, userLon);

            // Marqueur pour la ville cible
            googleMap.addMarker(new MarkerOptions()
                    .position(villeLatLng)
                    .title("Ville : " + villeCible));

            // Marqueur pour la position du joueur
            googleMap.addMarker(new MarkerOptions()
                    .position(userLatLng)
                    .title("Votre position")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

            // Zoom automatique pour englober les deux
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(villeLatLng);
            builder.include(userLatLng);
            LatLngBounds bounds = builder.build();

            int padding = 100; // espace autour des points
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
        });
    }

    private void localiserJoueur() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        userLat = location.getLatitude();
                        userLon = location.getLongitude();
                        calculerDistance();
                    }
                });
    }

    private void chargerVilleAleatoire() {
        new Thread(() -> {
            CityDao dao = AppDatabase.getInstance(this).cityDao();
            City ville = dao.getRandomCityObject(); // Attention: méthode différente de getRandomCity()

            runOnUiThread(() -> {
                if (ville != null) {
                    villeCible = ville.name;
                    villeLat = ville.latitude;
                    villeLon = ville.longitude;

                    //textVilleTiree.setText("Ville tirée : " + villeCible);
                    textVilleTiree.setText(getString(R.string.city_drawn_label, villeCible));

                    calculerDistance(); // Dès qu'on a la ville, on peut recalculer la distance
                }
            });
        }).start();
    }


//    private void calculerDistance() {
//        if (userLat != 0 && userLon != 0 && villeLat != 0 && villeLon != 0) {
//            vraieDistance = haversine(userLat, userLon, villeLat, villeLon);
//            submitButton.setEnabled(true);
//        }
//    }

    private void calculerDistance() {
        if (userLat != 0 && userLon != 0 && villeLat != 0 && villeLon != 0) {
            vraieDistance = haversine(userLat, userLon, villeLat, villeLon);
            submitButton.setEnabled(true);

//            mapView.getMapAsync(googleMap -> {
//                googleMap.clear();
//
//                LatLng villeLatLng = new LatLng(villeLat, villeLon);
//                LatLng userLatLng = new LatLng(userLat, userLon);
//
//                googleMap.addMarker(new MarkerOptions()
//                        .position(villeLatLng)
//                        .title("Ville : " + villeCible));
//
//                googleMap.addMarker(new MarkerOptions()
//                        .position(userLatLng)
//                        .title("Votre position")
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//
//                LatLngBounds.Builder builder = new LatLngBounds.Builder();
//                builder.include(villeLatLng);
//                builder.include(userLatLng);
//                LatLngBounds bounds = builder.build();
//
//                int padding = 100; // espace autour
//                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
//            });
        }
    }


    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Rayon de la Terre en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }

    private void verifierReponse() {
        String saisie = inputDistance.getText().toString().trim();
        if (saisie.isEmpty()) return;

        int guess = Integer.parseInt(saisie);
        essai++;

        if (Math.abs(guess - vraieDistance) <= 10) { // ±10 km de tolérance
            score++;
            Toast.makeText(this, "✅ Bonne réponse ! Score : " + score, Toast.LENGTH_SHORT).show();
            inputDistance.setText("");

            essai = 0;
            submitButton.setEnabled(false);
            userLat = 0;
            userLon = 0;
            villeLat = 0;
            villeLon = 0;
            vraieDistance = 0;

            chargerVilleAleatoire();
            localiserJoueur();
        } else if (essai < 3) {
            Toast.makeText(this, "❌ Raté ! Essai " + essai + "/3", Toast.LENGTH_SHORT).show();
            inputDistance.setText("");
        } else {
            SharedPreferences prefs = getSharedPreferences("distance_prefs", MODE_PRIVATE);
            if (score > bestScore) {
                prefs.edit().putInt(PREF_DISTANCE_BEST, score).apply();
                bestScore = score;
            }
            afficherFin();
        }
    }

    private void afficherFin() {
        new AlertDialog.Builder(this)
                .setTitle("Fin du jeu")
                .setMessage("❌ Fin de partie.\nScore : " + score + "\nMeilleur : " + bestScore)
                .setCancelable(false)
                .setPositiveButton("Rejouer", (dialog, which) -> recreate())
                .setNegativeButton("Retour", (dialog, which) -> finish())
                .show();
    }






    //gestion du cycle de vie de la MapView

    @Override
    protected void onStart() {
        super.onStart();
        if (mapView != null) {
            mapView.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (mapView != null) {
            mapView.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        if (mapView != null) {
            mapView.onStop();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mapView != null) {
            mapView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

}
