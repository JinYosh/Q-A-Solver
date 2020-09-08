package com.example.pikachu.googleeye;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by PiKaChU on 20-May-18.
 */

public class GrabImage {
    Bitmap bitmap;
    public Bitmap getImage(String location){
        bitmap = BitmapFactory.decodeFile(location);
        return bitmap;
    }
}
