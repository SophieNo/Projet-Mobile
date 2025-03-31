package com.example.projet.CircuitGame;

import android.graphics.Color;

public class Obstacle extends Element{

    public Obstacle(){
        color = Color.RED;
        setRayon(100);
    }

    public Obstacle(float x, float y){
        setX(x);
        setY(y);
        color = Color.RED;
        setRayon(50);
    }
}
