package com.example.projet.Champignon;

import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.projet.R;

import java.util.Random;

public class Phalloide extends Champignon {

    public Phalloide (Sousbois sb){
        sousBois = sb;
        view = new ImageView(sousBois.getContext());

        int width = sousBois.getWidth();
        int height = sousBois.getHeight();
        r = new Random();


        view.setImageResource(R.drawable.phalloide);
        sousBois.addView(view);

        int dim = sousBois.getDim();
        view.setLayoutParams(new ConstraintLayout.LayoutParams(dim,dim));

        float x = r.nextInt(width);
        float y = r.nextInt(height);

        view.setX(testX(x));
        view.setY(testY(y));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sousBois.removescore();
                sousBois.removeView(view);
            }
        });

        Runnable remove = new Runnable() {
            @Override
            public void run() {
                sousBois.removeView(view);
            }
        };

        sousBois.handler.postDelayed(remove, 2000);
    }

}
