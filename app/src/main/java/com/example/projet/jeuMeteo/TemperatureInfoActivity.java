package com.example.projet.jeuMeteo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;

public class TemperatureInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_info);

        Button jouer = findViewById(R.id.button_jouer_temp);
        Button retour = findViewById(R.id.button_retour_temp);

        TextView bestScoreText = findViewById(R.id.text_best_score_temp);
        SharedPreferences prefs = getSharedPreferences("temperature_prefs", MODE_PRIVATE);
        int bestScore = prefs.getInt("temp_game_best_score", 0);

        String formattedScore = getString(R.string.best_score, bestScore);
        bestScoreText.setText(formattedScore);


        jouer.setOnClickListener(v ->
                startActivity(new Intent(this, TemperatureGameActivity.class))
        );

        retour.setOnClickListener(v -> finish());
    }
}
