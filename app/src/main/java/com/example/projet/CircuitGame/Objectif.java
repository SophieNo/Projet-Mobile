package com.example.projet.CircuitGame;

import android.graphics.Color;

public class Objectif extends Element{

    public Objectif (){
        color = Color.BLUE;
        setRayon(70);
    }

    public Objectif (float x, float y){
        setX(x);
        setY(y);
        color = Color.BLUE;
        setRayon(70);
    }


}
