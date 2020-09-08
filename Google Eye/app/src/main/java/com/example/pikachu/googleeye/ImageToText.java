package com.example.pikachu.googleeye;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
/**
 * Created by PiKaChU on 14-May-18.
 */

public class ImageToText {

    String imageText;
    StringBuilder stringBuilder;
    public String getImageText(Bitmap bitmap, Context context){

        TextRecognizer recognizer;
        if (bitmap != null) {
            recognizer = new TextRecognizer.Builder(context).build();
            if (!recognizer.isOperational()) {

            } else {
                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<TextBlock> items = recognizer.detect(frame);

                stringBuilder = new StringBuilder();
                for (int i = 0; i < items.size(); ++i) {
                    TextBlock textBlock = items.valueAt(i);
                    stringBuilder.append(textBlock.getValue());
                    stringBuilder.append("\n");
                }




            }
        }
        else{
            Log.i("ImageToText","Image null");
        }


        imageText = stringBuilder.toString();
        return imageText;
    }
}
