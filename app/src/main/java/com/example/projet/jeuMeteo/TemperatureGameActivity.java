package com.example.projet.jeuMeteo;

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
import com.example.projet.R;
import com.google.android.gms.location.*;

import java.util.Random;

public class TemperatureGameActivity extends AppCompatActivity {

    private static final String PREF_TEMPERATURE_BEST = "temp_game_best_score";

    private TextView instructionText;
    private EditText inputDifference;
    private Button submitButton;
    private int essai = 0;
    private int solution = 0; // écart de température
    private String villeCible = "Paris"; // exemple fixe (à remplacer par tirage BDD)
    private double tempCible = 0.0;
    private double tempJoueur = 0.0;
    private TextView textVilleTiree ;
    private int score = 0;
    private int bestScore = 0;


    private FusedLocationProviderClient fusedLocationClient;

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
        setContentView(R.layout.activity_temperature_game);

        instructionText = findViewById(R.id.text_instruction);
        inputDifference = findViewById(R.id.input_difference);
        submitButton = findViewById(R.id.button_submit);

        Button boutonRetour = findViewById(R.id.button_retour_temp_game);
        boutonRetour.setOnClickListener(v -> finish());

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SharedPreferences prefs = getSharedPreferences("temperature_prefs", MODE_PRIVATE);
        bestScore = prefs.getInt(PREF_TEMPERATURE_BEST, 0);
        score = 0;


        // Demander localisation
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
                        getTemperaturePourLocation(location);
                    }
                });
    }


//    private void verifierReponse() {
//        String saisie = inputDifference.getText().toString().trim();
//        if (saisie.isEmpty()) return;
//
//        int guess = Integer.parseInt(saisie);
//        essai++;
//
//        if (guess == solution) {
//            afficherFin("Bravo 🎉 ! Bonne réponse : " + solution + "°");
//        } else if (essai == 3) {
//            String indice = (new Random().nextBoolean()) ?
//                    "La ville cible était : " + villeCible :
//                    "Votre température actuelle : " + tempJoueur + "°";
//            afficherFin("Dommage 😢 ! La bonne réponse était : " + solution + "°\n" + indice);
//        } else {
//            Toast.makeText(this, "Raté ! Encore " + (3 - essai) + " essai(s)", Toast.LENGTH_SHORT).show();
//            inputDifference.setText("");
//        }
//    }

    private void verifierReponse() {
        String saisie = inputDifference.getText().toString().trim();
        if (saisie.isEmpty()) return;

        int guess = Integer.parseInt(saisie);
        essai++;

        if (Math.abs(guess - solution) <= 1) {

            score++;
            Toast.makeText(this, "✅ Bonne réponse ! Score : " + score, Toast.LENGTH_SHORT).show();
            inputDifference.setText("");

            // Préparer la prochaine devinette
            essai = 0;
            tempCible = 0.0;
            tempJoueur = 0.0;
            submitButton.setEnabled(false);

            chargerVilleAleatoire();
            localiserJoueur();
        } else if (essai < 3) {
            Toast.makeText(this, "❌ Raté ! Essai " + essai + "/3", Toast.LENGTH_SHORT).show();
            inputDifference.setText("");
        } else {
            // Mauvaise réponse au 3e essai → fin du jeu
            SharedPreferences prefs = getSharedPreferences("temperature_prefs", MODE_PRIVATE);
            bestScore = prefs.getInt(PREF_TEMPERATURE_BEST, 0);

            if (score > bestScore) {
                prefs.edit().putInt(PREF_TEMPERATURE_BEST, score).apply();
                bestScore = score;
            }

            String indice = (new Random().nextBoolean()) ?
                    "La ville était : " + villeCible :
                    "Votre température : " + String.format("%.1f", tempJoueur) + "°";

            afficherFin("⛔ Mauvaise réponse...\nBonne réponse : " + solution + "°\n"
                    + indice + "\n\nScore : " + score + "\nMeilleur : " + bestScore);
        }

        TextView scoreText = findViewById(R.id.text_score);
        scoreText.setText("Score : " + score + " / Meilleur : " + bestScore);
    }


    private void afficherFin(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Fin du jeu")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Rejouer", (dialog, which) -> {
                    recreate();
                })
                .setNegativeButton("Retour", (dialog, which) -> {
                    finish();
                })
                .show();
    }

    // 🔧 À implémenter : vraie logique avec base de données
//    private String tirerVilleAleatoire() {
//        return CityDao.getRandomCity();
//    }

    private void chargerVilleAleatoire() {
        textVilleTiree = findViewById(R.id.text_ville_tiree);
        new Thread(() -> {
            CityDao dao = AppDatabase.getInstance(this).cityDao();
            String ville = dao.getRandomCity();

            runOnUiThread(() -> {
                villeCible = ville;
                textVilleTiree.setText("Ville tirée : " + villeCible);
                getTemperaturePourVille(villeCible); // température cible
            });
        }).start();
    }


    // 🔧 À implémenter : vraie requête API
    private void getTemperaturePourVille(String ville) {
        //return 17.0 + new Random().nextDouble() * 10; // temp aléatoire fictive
        TemperatureService.getTemperatureByCity(ville, new TemperatureService.TemperatureCallback() {
            @Override
            public void onResult(double temperature) {
                tempCible = temperature;
                checkBothTemperaturesReady();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(TemperatureGameActivity.this, "Erreur météo ville : " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getTemperaturePourLocation(Location location) {
        //return 15.0 + new Random().nextDouble() * 10; // temp aléatoire fictive
        TemperatureService.getTemperatureByLocation(location.getLatitude(), location.getLongitude(), new TemperatureService.TemperatureCallback() {
            @Override
            public void onResult(double temperature) {
                tempJoueur = temperature;
                checkBothTemperaturesReady();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(TemperatureGameActivity.this, "Erreur météo localisation : " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void checkBothTemperaturesReady() {
        if (tempCible != 0.0 && tempJoueur != 0.0) {
            solution = (int) Math.round(Math.abs(tempJoueur - tempCible));
            Toast.makeText(this, "Les températures sont prêtes. À vous de jouer !", Toast.LENGTH_SHORT).show();
            submitButton.setEnabled(true);
        }
    }

}
