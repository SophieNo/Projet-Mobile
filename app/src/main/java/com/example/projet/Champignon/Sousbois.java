package com.example.projet.Champignon;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.projet.R;

import java.util.Random;

public class Sousbois extends ConstraintLayout {
    private int score = 0;
    private int dim;
    Random random;
    Handler handler;
    Runnable create;
    Champignon champ;
    private TextView scoreView;
    private static final String PREFS_NAME = "champi_prefs";
    private static final String PREF_SCORE = "best_score";
    private static final String PREF_TOP_1 = "top_score_1";
    private static final String PREF_TOP_2 = "top_score_2";
    private static final String PREF_TOP_3 = "top_score_3";




    public int getDim() {
        return dim;
    }

    public Sousbois (Context context) {
        super(context);
        setWillNotDraw(false);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        dim = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, dm);

        handler = new Handler();
        random = new Random();

        setBackgroundResource(R.drawable.sous_bois);

        Sousbois sb = this;


        create = new Runnable() {
            @Override
            public void run() {
                float r = random.nextFloat();

                if (r < 0.33) {
                    champ = new Cepe(sb);
                }
                else if (r >= 0.33 && r < 0.66) {
                    champ = new Phalloide(sb);
                } else {
                    champ = new TueMouches(sb);
                }
                int time = random.nextInt(2000);
                handler.postDelayed(this,time);
            }
        };

        handler.postDelayed(create, 2000);

    }

    public void setScoreView(TextView view) {
        this.scoreView = view;
        updateScoreDisplay();
    }

    private void updateScoreDisplay() {
        if (scoreView != null) {
            scoreView.setText("Score : " + score);
        }
    }

    public void addscore (){
        score = score + 1;
        updateScoreDisplay();
        if ((score%10) == 0) {
            Toast.makeText(getContext(), "Nouveau palier : " + score + " points", Toast.LENGTH_LONG).show();
        }
    }

    public void removescore (){
        saveScore();
        score = 0;
        updateScoreDisplay();
        Toast.makeText(getContext(), "Très mauvaise idée", Toast.LENGTH_LONG).show();
    }

    public void removescore_tuemouches (){
        saveScore();
        if (score < 3){
            score = 0 ;
        } else {
            score = score - 3;
        }
        updateScoreDisplay();
    }

    private void saveScore() {
        int best = getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt(PREF_SCORE, 0);
        if (score > best) {
            getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .putInt(PREF_SCORE, score)
                    .apply();
        }
    }

    public void applyBlurTemporarily() {
        this.setAlpha(0.3f); // effet visuel de "flou"
        handler.postDelayed(() -> this.setAlpha(1f), 2000);
    }


}
