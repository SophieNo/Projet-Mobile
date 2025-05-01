package com.example.projet;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button boutonPhotoJeu;
    ImageView flagIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CitySeeder.seed(this);

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