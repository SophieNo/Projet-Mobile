package com.example.projet.JeuxPhoto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;

public class PhotoJeuInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_info);

        Button jouer = findViewById(R.id.button_jouer);
        Button retour = findViewById(R.id.button_retour_accueil);

        jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhotoJeuInfoActivity.this, FiltrePhoto.class);
                startActivity(intent);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Retour à la MainActivity automatiquement
            }
        });
    }
}
