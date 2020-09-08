package com.example.pikachu.googleeye;

import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.FileFileFilter;

/**
 * Created by PiKaChU on 20-May-18.
 */

public class FileList {
    String folderLocation;
    File dir;
    File[] fileList;

    public List getList(String appName){

        folderLocation = "/storage/sdcard0/pictures/"+appName;

        dir = new File(folderLocation);
        List<String> list = new ArrayList<String>();
        if(dir.exists()) {
            fileList = dir.listFiles();
        }
        else
            Log.i("FileList","Dir does not exists");

       /* Arrays.sort(fileList,(f1,f2)-> {
            if(f1.isDirectory() && !f2.isDirectory()){
                return -1;
            }
            else if(!f1.isDirectory() && f2.isDirectory()){
                return 1;
            }
            else{
                return f1.compareTo(f2);
            }
        });*/
        Arrays.sort(fileList, (f1, f2) -> {
            return new Date(f1.lastModified()).compareTo(new Date(f2.lastModified()));
        });

        for(File name:fileList){
            if(name.isFile() & name.exists()){
                if(name.toString().endsWith(".png")){
                    list.add(name.toString());
                }
            }
        }

        //Arrays.sort(fileList,NameFileComparator.NAME_COMPARATOR)

        return list;
    }
}
