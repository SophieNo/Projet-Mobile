package com.example.projet.DessinGame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ColorDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose a color");

        CharSequence [] couleur = {"red", "green", "black", "blue"};

        builder.setItems(couleur, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Activity t = getActivity();
                Dessin d = new Dessin (t);
                switch(i){
                    case 0:
                        d = new Dessin (t, Color.RED);
                        break;
                    case 1:
                        d = new Dessin (t, Color.GREEN);
                        break;
                    case 2 :
                        d = new Dessin (t, Color.BLACK);
                        break;
                    case 3 :
                        d = new Dessin (t, Color.BLUE);
                        break;

                }
                t.setContentView(d);

            }
        });
        return builder.create();
    }
}

