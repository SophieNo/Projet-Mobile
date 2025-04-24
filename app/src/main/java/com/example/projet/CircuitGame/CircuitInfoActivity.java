package com.example.projet.CircuitGame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;

public class CircuitInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuit_info);

        Button jouer = findViewById(R.id.button_jouer_circuit);
        Button retour = findViewById(R.id.button_retour_circuit);

        jouer.setOnClickListener(v ->
                startActivity(new Intent(this, CircuitGameActivity.class))
        );

        retour.setOnClickListener(v -> finish());
    }
}