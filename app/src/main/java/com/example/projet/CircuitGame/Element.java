package com.example.projet.CircuitGame;

import android.graphics.Paint;

public class Element {
    private float[] coord;
    private float rayon;
    int color;
    Paint p;

    public Element(){
        coord = new float[2];
        rayon = 0;
    }

    public Element (float[] c, float r){
        coord = c;
        rayon = r;
    }

    public void setRayon(float f){
        rayon = f;
    }

    public int getColor() {
        return color;
    }

    public float getX(){
        return coord[0];
    }
    public void setX(float x){
        coord[0] = x;
    }

    public float getY(){
        return coord[1];
    }
    public void setY(float y){
        coord[1] = y;
    }

    public float getRayon() {
        return rayon;
    }

    public boolean chevauche(Element element){
        float x1 = this.getX();
        float y1 = this.getY();
        float x2 = element.getX();
        float y2 = element.getY();

        float x = (x1-x2)*(x1-x2);
        float y = (y1-y2)*(y1-y2);

        double t = (double) (x-y);

        float fin =  (float) Math.sqrt(t);

        float rayon = this.getRayon() + element.getRayon();

        if (-rayon > fin  && fin < rayon ){
            return false;
        }

        return true;
    }
}
