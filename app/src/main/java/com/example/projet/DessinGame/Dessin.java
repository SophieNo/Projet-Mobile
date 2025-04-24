package com.example.projet.DessinGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Dessin extends View {

    ArrayList <Cercle> c;
    int col;
    float compte = 1f;

    public Dessin (Context context){
        super(context);
        c = new ArrayList<Cercle>();
        col = Color.BLACK;
    }

    public Dessin (Context context, int color){
        super(context);
        c = new ArrayList<Cercle>();
        col = color;

    }

    public void addList (Cercle e){
        c.add(e);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(col);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < c.size()-1; i++){
            Cercle temp = c.get(i);
            canvas.drawCircle(temp.getCoordX(), temp.getCoordY(), temp.getRayon(), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //compte = 1f;
                Cercle cer = new Cercle(event.getX(), event.getY(), 1f);
                addList(cer);
                compte = compte + 1f;

                break;
            case MotionEvent.ACTION_UP:
                compte = 1f;

                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getHistorySize(); i++) {
                    Cercle ce = new Cercle(event.getHistoricalX(i), event.getHistoricalY(i), compte);
                    addList(ce);
                    compte = compte + 1f;
                }
                break;
        }
        invalidate();
        return true;
    }
}

