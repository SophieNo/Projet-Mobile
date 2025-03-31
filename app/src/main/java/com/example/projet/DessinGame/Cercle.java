package com.example.projet.DessinGame;

public class Cercle {
    private float rayon;
    private float coordX;
    private float coordY;

    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    public float getCoordX() {
        return coordX;
    }

    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }

    public float getCoordY() {
        return coordY;
    }

    public void setRayon(float rayon) {
        this.rayon = rayon;
    }

    public float getRayon() {
        return rayon;
    }

    public Cercle (float x, float y, float r) {
        rayon = r;
        coordX = x;
        coordY = y;
    }
}

