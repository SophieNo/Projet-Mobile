package com.example.projet.DessinGame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;
public class DessinInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessin_info);

        Button jouer = findViewById(R.id.button_jouer_dessin);
        Button retour = findViewById(R.id.button_retour_dessin);

        jouer.setOnClickListener(v -> {
            startActivity(new Intent(this, DessinGameActivity.class));
        });

        retour.setOnClickListener(v -> finish());
    }
}
