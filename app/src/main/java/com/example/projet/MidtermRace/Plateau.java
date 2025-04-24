package com.example.projet.MidtermRace;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

public class Plateau extends View {
    int position_Rep = 0;
    int position_Dem = 5;
    Context context;

    public Plateau(Context context){
        super(context);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2, paint);

        paint.setStyle(Paint.Style.STROKE);

        float[][] centers = computeCenters();

        for (int i = 0; i < 10; i++){
            paint.setStyle(Paint.Style.FILL);
            if (i == position_Rep) paint.setColor(Color.RED);
            else if (i== position_Dem) paint.setColor(Color.BLUE);
            else {
                paint.setColor(Color.BLACK);
                paint.setStyle(Paint.Style.STROKE);
            }
            canvas.drawCircle(centers[i][0], centers[i][1] , computeRadius(), paint);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getY() < getHeight()/2){
                    if (position_Rep < 9) position_Rep++;
                    else position_Rep = 0;
                    if (position_Rep == position_Dem) {
                        Toast.makeText(context, "Les Républicains contrôlent le Sénat !", Toast.LENGTH_LONG).show();
                        fin_de_jeu();
                    }
                } else {
                    if (position_Dem < 9) position_Dem++;
                    else position_Dem = 0;
                    if (position_Rep == position_Dem) {
                        Toast.makeText(context, "Les Démocrates contrôlent le Sénat !", Toast.LENGTH_LONG).show();
                        fin_de_jeu();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    private float [] [] computeCenters () {
        float[][] centers = new float [10][2] ;
        float w = getWidth (),h= getHeight () ;
        float radius = Math.min(w,h)/4 ;
        float centerX = w/ 2 , centerY = h / 2 ;
        for ( int i = 0 ; i <10; i ++){
            double angle = Math.PI/2 + 2*i*(Math.PI/10) ;
            centers[i][0] = (float) (centerX+Math.cos(angle)*radius) ;
            centers[i][1] = (float) (centerY-Math.sin(angle)*radius);
        }
        return centers ;
    }

    private float computeRadius ( ) {
        return Math.min(getWidth(), getHeight())/20;
    }

    private void fin_de_jeu (){
        position_Rep = 0;
        position_Dem = 5;
    }


}
