package com.example.projet.MidtermRace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;

public class MidtermRaceActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midterm_race);

        // Créer dynamiquement le jeu (qui hérite de View)
        Plateau jeu = new Plateau(this);

        // Ajouter la vue du jeu dans le conteneur prévu dans le layout
        FrameLayout frameLayout = findViewById(R.id.jeu_container);
        frameLayout.addView(jeu);

        // Bouton retour
        Button retour = findViewById(R.id.button_retour_mr);
        retour.setOnClickListener(v -> finish());
    }
}
