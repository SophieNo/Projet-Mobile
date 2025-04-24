package com.example.projet.DessinGame;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DessinGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Affiche la boîte de dialogue de couleur dès le lancement
        ColorDialog dialog = new ColorDialog();
        dialog.show(getSupportFragmentManager(), "choix_couleur");
    }
}
