package com.example.projet.JeuDistance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;

public class DistanceInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_info);

        Button jouer = findViewById(R.id.button_jouer_distance);
        Button retour = findViewById(R.id.button_retour_distance_info);

        jouer.setOnClickListener(v -> {
            startActivity(new Intent(this, DistanceGameActivity.class));
        });

        retour.setOnClickListener(v -> finish());
    }
}
