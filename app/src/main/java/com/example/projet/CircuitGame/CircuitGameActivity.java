package com.example.projet.CircuitGame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CircuitGameActivity extends AppCompatActivity {

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private SensorEventListener listener;
    private Terrain terrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crée la vue du jeu
        terrain = new Terrain(this);
        setContentView(terrain);

        // Récupération des capteurs
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        boolean present = false;
        for (Sensor sensor : sensorList) {
            if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                present = true;
                accelerometer = sensor;
                break;
            }
        }

        if (!present) {
            Log.v("CircuitGame", "Aucun accéléromètre détecté.");
            finish();
            return;
        }

        // Listener pour suivre l'accélération
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values.clone(); // x, y, z
                terrain.roll(values);
                terrain.invalidate();
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Rien à faire ici
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
    }
}