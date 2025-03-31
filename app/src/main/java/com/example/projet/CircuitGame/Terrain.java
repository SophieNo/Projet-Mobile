package com.example.projet.CircuitGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Terrain extends View {
    Bille bille;
    Objectif objectif;
    ArrayList<Obstacle> obstacles;
    int compte;

   /* public Terrain (){
        bille = null;
        objectif = null;
        obstacles = new ArrayList<Obstacle>();
    } */

    public Terrain (Context context){
        super(context);
        bille = null;
        objectif = null;
        obstacles = new ArrayList<Obstacle>();
        compte = 0;
    }

    public void addList(Obstacle o) { obstacles.add(o);}


    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        //paint.setStyle(Paint.Style.STROKE);

        if (bille != null) {
            paint.setColor(bille.getColor());
            canvas.drawCircle(bille.getX(), bille.getY(), bille.getRayon(), paint);
        }

        if (objectif != null) {
            paint.setColor(objectif.getColor());
            canvas.drawCircle(objectif.getX(), objectif.getY(), objectif.getRayon(), paint);
        }

        for(int i = 0; i < obstacles.size()-1; i++){
            Obstacle temp = obstacles.get(i+1);
            paint.setColor(temp.getColor());
            canvas.drawCircle(temp.getX(), temp.getY(), temp.getRayon(), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (objectif == null){
                    objectif = new Objectif(event.getX(), event.getY());
                } else if (bille == null){
                    bille = new Bille(event.getX(), event.getY());
                } else {
                    Obstacle o = new Obstacle(event.getX(), event.getY());
                    addList(o);
                }
                compte = compte + 1;
                break;
            case MotionEvent.ACTION_UP:
                /*if (compte == 0){
                    objectif = new Objectif(event.getX(), event.getY());
                } else if (compte == 1){
                    bille = new Bille(event.getX(), event.getY());
                } else {
                    Obstacle o = new Obstacle(event.getX(), event.getY());
                    addList(o);
                }
                compte = compte + 1;*/
                break;
            case MotionEvent.ACTION_MOVE: break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    public boolean roll(float[] deplacement){
        if (bille != null) {
            float t = (bille.getX() - deplacement[0]);
            if (t > getWidth()) {
                bille.setX(getWidth() - bille.getRayon());
            } else if (t < 0) {
                bille.setX(0 + bille.getRayon());
            } else bille.setX(t);

            float temp = bille.getY() + deplacement[1];
            if (temp > getHeight()) {
                bille.setY(getHeight() - bille.getRayon());
            } else if (temp < 0) {
                bille.setY(0 + bille.getRayon());
            } else bille.setY(temp);

        }

        return true;
    }

    public boolean checkPosition(){

        return true;
    }
}
