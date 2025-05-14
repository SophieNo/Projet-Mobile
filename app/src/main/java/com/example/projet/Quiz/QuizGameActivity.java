package com.example.projet.Quiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projet.R;

import java.util.List;

public class QuizGameActivity extends AppCompatActivity {

    private TextView questionText, scoreText;
    private Button buttonA, buttonB, buttonC, buttonD;
    private QuizGame quizGame;
    private Question currentQuestion;
    private int bestScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        questionText = findViewById(R.id.text_question);
        scoreText = findViewById(R.id.text_score);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

//        quizGame = new QuizGame();
//        loadNextQuestion();
        bestScore = getSharedPreferences("quiz", MODE_PRIVATE).getInt("best", 0);

        new Thread(() -> {
            quizGame = new QuizGame(); // La requête API se fait ici
            runOnUiThread(() -> loadNextQuestion()); // Une fois fini, on affiche la première question
        }).start();




        buttonA.setOnClickListener(v -> handleAnswer("A"));
        buttonB.setOnClickListener(v -> handleAnswer("B"));
        buttonC.setOnClickListener(v -> handleAnswer("C"));
        buttonD.setOnClickListener(v -> handleAnswer("D"));
    }

    private void loadNextQuestion() {
        if (quizGame.hasNext()) {
            currentQuestion = quizGame.nextQuestion();
            questionText.setText(currentQuestion.getQuestion());
            buttonA.setText("A. " + currentQuestion.getReponseA());
            buttonB.setText("B. " + currentQuestion.getReponseB());
            buttonC.setText("C. " + currentQuestion.getReponseC());
            buttonD.setText("D. " + currentQuestion.getReponseD());
            scoreText.setText("Score : " + quizGame.getScore());
        } else {
            showEndGame();
        }
    }

    private void handleAnswer(String userAnswer) {
        quizGame.submitAnswer(userAnswer);
        loadNextQuestion();
    }

    private void showEndGame() {
        int score = quizGame.getScore();
        if (score > bestScore) {
            getSharedPreferences("quiz", MODE_PRIVATE)
                    .edit().putInt("best", score).apply();
        }

        new AlertDialog.Builder(this)
                .setTitle("Partie terminée")
                .setMessage("Score : " + score + "\nMeilleur score : " +
                        Math.max(bestScore, score))
                .setPositiveButton("Rejouer", (d, w) -> recreate())
                .setNegativeButton("Accueil", (d, w) -> finish())
                .setCancelable(false)
                .show();
    }
}

