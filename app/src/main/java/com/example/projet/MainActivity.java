package com.example.projet;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Handler;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projet.BDDville.CitySeeder;
import com.example.projet.Champignon.ChampiInfoActivity;
import com.example.projet.CircuitGame.CircuitInfoActivity;
import com.example.projet.DessinGame.DessinInfoActivity;
import com.example.projet.JeuDistance.DistanceInfoActivity;
import com.example.projet.JeuxPhoto.PhotoJeuInfoActivity;
import com.example.projet.MidtermRace.MidtermRaceInfoActivity;
import com.example.projet.jeuMeteo.TemperatureInfoActivity;
import com.example.projet.Quiz.QuizDescriptionActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;
import android.Manifest;


public class MainActivity extends AppCompatActivity {

    Button boutonPhotoJeu;
    ImageView flagIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init de la BDD interne
        CitySeeder.seed(this);

        // Partie sur le choix de la langue
        flagIcon = findViewById(R.id.flag_icon);

        String language = getResources().getConfiguration().locale.getLanguage();

        if (language.equals("fr")) {
            flagIcon.setImageResource(R.drawable.ic_flag_fr); // 🇫🇷 ton image
        } else {
            flagIcon.setImageResource(R.drawable.ic_flag_gb); // 🇬🇧 ton image
        }

        flagIcon.setOnClickListener(v -> {
            String languagez = getResources().getConfiguration().locale.getLanguage();

            if (languagez.equals("fr")) {
                changerLangue("en");
            } else {
                changerLangue("fr");
            }
        });

        flagIcon.setEnabled(false);
        new Handler().postDelayed(() -> flagIcon.setEnabled(true), 1000);


        TextView addressText = findViewById(R.id.text_user_address);

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if (!addresses.isEmpty()) {
                            String adresse = addresses.get(0).getAddressLine(0);
                            addressText.setText("📍 " + adresse);
                        } else {
                            addressText.setText("Adresse introuvable");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        addressText.setText("Erreur géolocalisation");
                    }
                }
            });
        }



        // Affichage des jeux
        LinearLayout cardPhoto = findViewById(R.id.card_photo);
        LinearLayout cardMidterm = findViewById(R.id.card_midterm);

        cardPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PhotoJeuInfoActivity.class);
            startActivity(intent);
        });

        cardMidterm.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MidtermRaceInfoActivity.class);
            startActivity(intent);
        });


        LinearLayout cardChampi = findViewById(R.id.card_champi);
        cardChampi.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChampiInfoActivity.class);
            startActivity(intent);
        });

        LinearLayout cardDessin = findViewById(R.id.card_dessin);
        cardDessin.setOnClickListener(v -> {
            startActivity(new Intent(this, DessinInfoActivity.class));
        });

        LinearLayout cardCircuit = findViewById(R.id.card_circuit);
        cardCircuit.setOnClickListener(v -> {
            startActivity(new Intent(this, CircuitInfoActivity.class));
        });

        LinearLayout cardTemp = findViewById(R.id.card_temp);
        cardTemp.setOnClickListener(v -> {
            startActivity(new Intent(this, TemperatureInfoActivity.class));
        });

        LinearLayout cardDistance = findViewById(R.id.card_distance);
        cardDistance.setOnClickListener(v -> {
            startActivity(new Intent(this, DistanceInfoActivity.class));
        });

        LinearLayout cardQuiz = findViewById(R.id.card_quiz);
        cardQuiz.setOnClickListener(v -> {
            startActivity(new Intent(this, QuizDescriptionActivity.class));
        });


    }


    private void changerLangue(String langue) {
        Locale locale = new Locale(langue);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        String language = getResources().getConfiguration().locale.getLanguage();
        if (language.equals("fr")) {
            flagIcon.setImageResource(R.drawable.ic_flag_fr); // 🇫🇷 ton image
        } else {
            flagIcon.setImageResource(R.drawable.ic_flag_gb); // 🇬🇧 ton image
        }

        // Redémarrer seulement MainActivity sans animation brutale
        rafraichirTextes();
    }

    private void rafraichirTextes() {
        TextView titre = findViewById(R.id.titre);
        titre.setText(R.string.name_app);

        ((TextView) ((LinearLayout) findViewById(R.id.card_photo)).getChildAt(1)).setText(R.string.name_photo);
        ((TextView) ((LinearLayout) findViewById(R.id.card_midterm)).getChildAt(1)).setText(R.string.name_midterm_race);
        ((TextView) ((LinearLayout) findViewById(R.id.card_champi)).getChildAt(1)).setText(R.string.name_champi);
        ((TextView) ((LinearLayout) findViewById(R.id.card_dessin)).getChildAt(1)).setText(R.string.name_dessin);
        ((TextView) ((LinearLayout) findViewById(R.id.card_circuit)).getChildAt(1)).setText(R.string.name_circuit);
        ((TextView) ((LinearLayout) findViewById(R.id.card_temp)).getChildAt(1)).setText(R.string.name_temperature_game);
        ((TextView) ((LinearLayout) findViewById(R.id.card_distance)).getChildAt(1)).setText(R.string.name_distance_game);
        ((TextView) ((LinearLayout) findViewById(R.id.card_quiz)).getChildAt(1)).setText(R.string.name_quiz);

    }

    //findViewById(R.id.card_...) récupère ton LinearLayout de la carte
    //.getChildAt(1) va chercher le TextView qui est le 2ᵉ enfant dans chaque carte
    //.setText(R.string.XXX) applique la nouvelle chaîne traduite automatiquement


}