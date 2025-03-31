package com.example.projet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.content.Intent;
import android.widget.LinearLayout;

import com.example.projet.Champignon.ChampiInfoActivity;
import com.example.projet.DessinGame.DessinInfoActivity;
import com.example.projet.JeuxPhoto.PhotoJeuInfoActivity;
import com.example.projet.MidtermRace.MidtermRaceInfoActivity;

public class MainActivity extends AppCompatActivity {

    Button boutonPhotoJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

    }
}