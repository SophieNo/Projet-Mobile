package com.example.projet.MidtermRace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;

public class MidtermRaceInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midterm_race_info);

        Button jouer = findViewById(R.id.button_jouer_mr);
        Button retour = findViewById(R.id.button_retour_mr_info);

        jouer.setOnClickListener(v -> {
            startActivity(new Intent(this, MidtermRaceActivity.class));
        });

        retour.setOnClickListener(v -> finish());
    }
}