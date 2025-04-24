package com.example.projet.CircuitGame;

import android.graphics.Color;

public class Bille extends Element{


    public Bille (){
        color = Color.BLACK;
        setRayon(100);
    }

    public Bille (float x, float y){
        setX(x);
        setY(y);
        color = Color.BLACK;
        setRayon(10);
    }

    @Override
    public int getColor() {
        return super.getColor();
    }
}