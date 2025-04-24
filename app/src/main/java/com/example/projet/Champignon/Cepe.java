package com.example.projet.Champignon;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.projet.R;

import java.util.Random;
public class Cepe extends Champignon  {
    public Cepe (Sousbois sb) {
        sousBois = sb;
        view = new ImageView(sousBois.getContext());

        int width = sousBois.getWidth();
        int height = sousBois.getHeight();
        r = new Random();


        view.setImageResource(R.drawable.cepe);
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
                sousBois.addscore();
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

    public float testX (float x){
        float dim = sousBois.getDim();
        if (x > (sousBois.getWidth()-dim)){
            x = sousBois.getWidth() - dim;
        } else if ( x < dim) {
            x = dim;
        }
        return x;
    }

    public float testY (float y){
        float dim = sousBois.getDim();
        float max_height = sousBois.getHeight() -dim;
        if (y > max_height) y = max_height;
        else if ( y < dim) y = dim;
        return y;
    }
}
