package com.example.pikachu.googleeye;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PiKaChU on 14-May-18.
 */

public class ScreenImage {
    private Bitmap bitmap;
    public Bitmap getScreenImage() {
        String folderLocation = "/storage/sdcard0/pictures/screenshots";

        try {
            List<String> listFiles = new ArrayList<String>();
            File files = new File(folderLocation);
            for (File file : files.listFiles()) {
                if (file.isFile() & file.exists()) {
                    if (file.toString().endsWith("png")) {
                        listFiles.add(file.toString());
                    }
                }
            }

            String lastname = listFiles.get(listFiles.size() - 1);

            bitmap = BitmapFactory.decodeFile(lastname);

        }catch (Exception e){

        }
        return bitmap;
    }
}
