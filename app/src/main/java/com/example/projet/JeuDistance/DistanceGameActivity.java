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

                    textVilleTiree.setText("Ville tirée : " + villeCible);

                    calculerDistance(); // Dès qu'on a la ville, on peut recalculer la distance
                }
            });
        }).start();
    }


    private void calculerDistance() {
        if (userLat != 0 && userLon != 0 && villeLat != 0 && villeLon != 0) {
            vraieDistance = haversine(userLat, userLon, villeLat, villeLon);
            submitButton.setEnabled(true);
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
}
