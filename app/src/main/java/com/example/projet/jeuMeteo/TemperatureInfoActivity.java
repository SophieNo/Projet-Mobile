package com.example.projet.jeuMeteo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;

public class TemperatureInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_info);

        Button jouer = findViewById(R.id.button_jouer_temp);
        Button retour = findViewById(R.id.button_retour_temp);

        jouer.setOnClickListener(v ->
                startActivity(new Intent(this, TemperatureGameActivity.class))
        );

        retour.setOnClickListener(v -> finish());
    }
}
