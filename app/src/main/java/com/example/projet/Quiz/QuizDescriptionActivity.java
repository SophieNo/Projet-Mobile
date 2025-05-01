package com.example.projet.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;

public class QuizDescriptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_description);

        TextView scoreView = findViewById(R.id.text_meilleur_score);
        int bestScore = getSharedPreferences("quiz", MODE_PRIVATE).getInt("best", 0);
        scoreView.setText("Meilleur score : " + bestScore);

        Button jouer = findViewById(R.id.button_jouer_quiz);
        Button retour = findViewById(R.id.button_retour_quiz);

        jouer.setOnClickListener(v -> startActivity(new Intent(this, QuizGameActivity.class)));
        retour.setOnClickListener(v -> finish());
    }
}