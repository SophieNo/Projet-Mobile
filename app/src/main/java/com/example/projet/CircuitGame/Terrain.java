package com.example.projet.CircuitGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Terrain extends View {
    Bille bille;
    Objectif objectif;
    ArrayList<Obstacle> obstacles;
    int compte;
    private Context context;
    private boolean finDePartie = false;


   /* public Terrain (){
        bille = null;
        objectif = null;
        obstacles = new ArrayList<Obstacle>();
    } */

    public Terrain (Context context){
        super(context);
        this.context = context;
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

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        switch(event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                if (objectif == null){
//                    objectif = new Objectif(event.getX(), event.getY());
//                } else if (bille == null){
//                    bille = new Bille(event.getX(), event.getY());
//                } else {
//                    Obstacle o = new Obstacle(event.getX(), event.getY());
//                    addList(o);
//                }
//                compte = compte + 1;
//                break;
//            case MotionEvent.ACTION_UP:
//                if (compte == 0){
//                    objectif = new Objectif(event.getX(), event.getY());
//                } else if (compte == 1){
//                    bille = new Bille(event.getX(), event.getY());
//                } else {
//                    Obstacle o = new Obstacle(event.getX(), event.getY());
//                    addList(o);
//                }
//                compte = compte + 1;
//                break;
//            case MotionEvent.ACTION_MOVE: break;
//        }
//        invalidate();
//        return super.onTouchEvent(event);
//    }

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


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN && !finDePartie) {
//            float x = event.getX();
//            float y = event.getY();
//
//            if (compte == 0) {
//                objectif = new Objectif(x, y);
//            } else if (compte == 1) {
//                bille = new Bille(x, y);
//            } else {
//                Obstacle newObstacle = new Obstacle(x, y);
//
//                // Vérifier qu'on ne crée pas l'obstacle trop près de la bille ou de l’objectif
//                boolean tropProche = false;
//                if (bille != null && distance(bille.getX(), bille.getY(), x, y) < 2 * bille.getRayon()) {
//                    tropProche = true;
//                }
//                if (objectif != null && distance(objectif.getX(), objectif.getY(), x, y) < 2 * objectif.getRayon()) {
//                    tropProche = true;
//                }
//
//                if (!tropProche) {
//                    obstacles.add(newObstacle);
//                } else {
//                    Toast.makeText(getContext(), "Obstacle trop proche de la bille ou de la cible", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            compte++;
//            invalidate();
//        }
//
//        return true;
//    }

    // Méthode utilitaire
    private float distance(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }


//    public boolean roll(float[] deplacement) {
//        if (bille != null && !finDePartie) {
//
//            // Calcul des nouvelles coordonnées
//            float newX = bille.getX() - deplacement[0];
//            float newY = bille.getY() + deplacement[1];
//
//            // Bordures de l'écran
//            newX = Math.max(bille.getRayon(), Math.min(newX, getWidth() - bille.getRayon()));
//            newY = Math.max(bille.getRayon(), Math.min(newY, getHeight() - bille.getRayon()));
//
//            bille.setX(newX);
//            bille.setY(newY);
//
//            // Défaite : collision avec un obstacle
//            for (Obstacle o : obstacles) {
//                if (bille.chevauche(o)) {
//                    finDePartie = true;
//                    afficherFinPartie("Perdu ! La bille a touché un obstacle.");
//                    return false;
//                }
//            }
//
//            // Victoire : collision avec objectif (mais pas immédiatement après la création)
//            if (objectif != null && bille != null) {
//                float dx = bille.getX() - objectif.getX();
//                float dy = bille.getY() - objectif.getY();
//                float dist = (float) Math.sqrt(dx * dx + dy * dy);
//
//                if (dist < bille.getRayon() + objectif.getRayon() - 2f) { // marge de sécurité
//                    finDePartie = true;
//                    afficherFinPartie("Gagné ! La bille a atteint l’objectif.");
//                    return true;
//                }
//            }
//        }
//
//        return true;
//    }

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


    private void afficherFinPartie(String message) {
        new android.app.AlertDialog.Builder(context)
                .setTitle("Fin de partie")
                .setMessage(message + "\nQue souhaitez-vous faire ?")
                .setCancelable(false)
                .setPositiveButton("Rejouer", (dialog, which) -> {
                    bille = null;
                    objectif = null;
                    obstacles.clear();
                    compte = 0;
                    finDePartie = false;
                    invalidate();
                })
                .setNegativeButton("Retour", (dialog, which) -> {
                    if (context instanceof android.app.Activity) {
                        ((android.app.Activity) context).finish();
                    }
                })
                .show();
    }


    public boolean checkPosition(){

        return true;
    }
}


/*
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
 */