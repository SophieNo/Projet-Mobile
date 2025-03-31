package com.example.projet.Champignon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;

public class ChampiInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champi_info);

        Button jouer = findViewById(R.id.button_jouer_champi);
        Button retour = findViewById(R.id.button_retour_champi_info);

        jouer.setOnClickListener(v -> startActivity(new Intent(this, ChampiGameActivity.class)));
        retour.setOnClickListener(v -> finish());

        SharedPreferences prefs = getSharedPreferences("champi_prefs", MODE_PRIVATE);
        int best = prefs.getInt("best_score", 0);

        TextView bestScore = findViewById(R.id.best_score_view);
        bestScore.setText("Meilleur score : " + best);

    }
}