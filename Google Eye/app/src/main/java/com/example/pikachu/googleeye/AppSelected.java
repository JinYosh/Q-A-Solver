package com.example.pikachu.googleeye;

import android.util.Log;

/**
 * Created by PiKaChU on 13-May-18.
 */

public class AppSelected {

    public static String selectedApp;

    public String getappname(){

        if(selectedApp == null){
            selectedApp = "no App";
        }

        return selectedApp;

    }
    public void setappname(String appname){

        selectedApp = appname;
        Log.i("AppSelected","app: "+selectedApp);
    }
}
