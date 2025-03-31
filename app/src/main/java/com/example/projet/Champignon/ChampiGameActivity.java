package com.example.projet.Champignon;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;
import com.example.projet.Champignon.Sousbois;

public class ChampiGameActivity extends AppCompatActivity {

    private long lastBackPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champi_game);

        // Créer et insérer la vue de jeu (Sousbois)
        Sousbois jeu = new Sousbois(this);
        jeu.setScoreView(findViewById(R.id.score_view));

        FrameLayout container = findViewById(R.id.container_champi);
        container.addView(jeu);
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBackPressedTime < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Appuyez à nouveau pour quitter", Toast.LENGTH_SHORT).show();
            lastBackPressedTime = currentTime;
        }
    }

}
