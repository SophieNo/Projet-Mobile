package com.example.projet.JeuxPhoto;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projet.MainActivity;
import com.example.projet.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FiltrePhoto extends AppCompatActivity  {

    Bitmap p;
    Button button = null;
    Button button2;
    Button button_inverse;
    Button button_flou;
    ImageView im;

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        setButton(savedInstanceState);
    }

    public void setButton (Bundle savedInstanceState){
        button = findViewById (R.id.button);
        button2 = findViewById (R.id.button2);
        button_inverse = findViewById (R.id.inverse);
        button_flou = findViewById (R.id.button4);
        //EditText textView = findViewById(R.id.textView);
        //TextView text = findViewById(R.id.text);
        im = findViewById(R.id.imageView);

        button.setOnClickListener  (new View.OnClickListener() {
            public void onClick (View view){
                Toast.makeText (getApplicationContext(), "Clic !", Toast.LENGTH_LONG).show();
                takePhoto.launch (null);
            }
        });

        button2.setOnClickListener  (new View.OnClickListener() {
            public void onClick (View view){
                Toast.makeText (getApplicationContext(), "Clic2 !", Toast.LENGTH_LONG).show();
                permute (p);
            }
        });

        button_inverse.setOnClickListener  (new View.OnClickListener() {
            public void onClick (View view){
                //Toast.makeText (getApplicationContext(), "Inversé !", Toast.LENGTH_LONG).show();
                inverse();
            }
        });

        button_flou.setOnClickListener  (new View.OnClickListener() {
            public void onClick (View view){
                //Toast.makeText (getApplicationContext(), "Inversé !", Toast.LENGTH_LONG).show();
                flouter();
            }
        });

        Button buttonRetour = findViewById(R.id.button_retour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FiltrePhoto.this, MainActivity.class);
                startActivity(intent);
                finish(); // Pour éviter que l'utilisateur revienne en arrière sur cette activité
            }
        });

        Button button_enregistrer = findViewById(R.id.button_enregistrer);
        button_enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enregistrerPhoto();
            }
        });


    }


    private ActivityResultCallback <Bitmap> callback = new ActivityResultCallback<Bitmap>() {
        @Override
        public void onActivityResult(Bitmap result) {
            p =  result.copy(Bitmap.Config.ARGB_8888,true);
            im.setImageBitmap(p);
        }
    };

    private ActivityResultContract <Void, Bitmap>  contract = new ActivityResultContract<Void, Bitmap>() {
        //
        @Override
        public Intent createIntent(@NonNull Context context, Void unused) {
            return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //return null;
        }

        @Override
        public Bitmap parseResult(int i, @Nullable Intent result) {
            return (Bitmap) result.getExtras().get("data");
        }
    };

    ActivityResultLauncher <Void> takePhoto = registerForActivityResult(contract, callback);

    public void permute (Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int red;
        int green;
        int blue;

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j ++){
                red = 0;
                green = 0;
                blue = 0;
                int c = bitmap.getPixel(i,j);

                red = Color.red(c);
                green = Color.green(c);
                blue = Color.blue(c);

                int col = Color.rgb (blue, red, green);
                //int col = Color.rgb (blue, blue, green);

                bitmap.setPixel(i, j, col);
            }
        }
    }

    public void inverse (){
        int width = p.getWidth();
        int height = p.getHeight();

        Bitmap bitmap = p.createBitmap(width, height, p.getConfig());

        int red;
        int green;
        int blue;

        for (int i = 0; i < width; i++){
            //
            for (int j = 0; j < height; j++){

                int c = p.getPixel(i,j);

                red = Color.red(c);
                green = Color.green(c);
                blue = Color.blue(c);

                int col = Color.rgb (red, green, blue);

                bitmap.setPixel(width-i-1, height-j-1, col);
            }
        }
        p = bitmap;
        im.setImageBitmap(p);
    }

    public void flouter() {
        int width = p.getWidth();
        int height = p.getHeight();

        Bitmap blurred = Bitmap.createBitmap(width, height, p.getConfig());

        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                int r = 0, g = 0, b = 0;

                // Moyenne sur les 9 pixels (carré 3x3)
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int pixel = p.getPixel(x + dx, y + dy);
                        r += Color.red(pixel);
                        g += Color.green(pixel);
                        b += Color.blue(pixel);
                    }
                }

                r /= 9;
                g /= 9;
                b /= 9;

                blurred.setPixel(x, y, Color.rgb(r, g, b));
            }
        }

        p = blurred;
        im.setImageBitmap(p);
    }



    public void enregistrerPhoto() {
        if (p == null) {
            Toast.makeText(this, "Aucune image à enregistrer", Toast.LENGTH_SHORT).show();
            return;
        }

        String nomFichier = "photo_" + System.currentTimeMillis() + ".jpg";
        OutputStream fos;

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, nomFichier);
                contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/JeuxPhotos");

                Uri imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                fos = getContentResolver().openOutputStream(imageUri);
            } else {
                String imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                File image = new File(imagesDir, nomFichier);
                fos = new FileOutputStream(image);
            }

            // Sauvegarde dans le flux
            p.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            Toast.makeText(this, "Image enregistrée !", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show();
        }
    }

}


/*
            public void registerForActivityResult (Bitmap b) {
                    val bitmap= Bitmap::copy(Bitmap.Config.ARGB_8888,true);
                    im = ImageView::setImageBitmap();

                };
 */
