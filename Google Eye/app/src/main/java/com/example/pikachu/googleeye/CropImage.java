package com.example.pikachu.googleeye;

import android.graphics.Bitmap;
import android.graphics.Rect;
/**
 * Created by PiKaChU on 14-May-18.
 */

public class CropImage {
    private Bitmap bitmap;

    public Bitmap getImage(Bitmap orgImage, Rect resolution){
        if(orgImage!=null) {
            bitmap = Bitmap.createBitmap(orgImage, resolution.left, resolution.top, resolution.right, resolution.bottom);
        }
        return bitmap;
    }


}
