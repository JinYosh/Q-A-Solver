package com.example.pikachu.googleeye;

import android.graphics.Rect;
import android.util.Log;

/**
 * Created by PiKaChU on 14-May-18.
 */

public class ResolutionSet {

    public AppResolution appResolution;
    public static Rect rectq,recta;

    public void setResolution(String appName){
        Log.i("App Resolution","appName: "+appName);
        if(appName.equals("Loco")){
            rectq = new Rect(0,550,700,130); //450 250
            recta = new Rect(0,680,700,405);
            appResolution = new AppResolution();
            appResolution.setResolution(appName,rectq,recta);
            appResolution.getResolutionQ(appName);
            //Log.i("Resolution Set","ques: "+appResolution.getResolutionQ(appName));
            appResolution.getResolutionA(appName);
            //Log.i("Resolution Set","ans: "+appResolution.getResolutionA(appName));
        }
        else if(appName.equals("Qureka")){
            rectq = new Rect(0,411,700,160);
            recta = new Rect(0,600,700,500);
            appResolution = new AppResolution();
            appResolution.setResolution(appName,rectq,recta);
            appResolution.getResolutionQ(appName);
            //Log.i("Resolution Set","ques: "+appResolution.getResolutionQ(appName));
            appResolution.getResolutionA(appName);
            //Log.i("Resolution Set","ans: "+appResolution.getResolutionA(appName));


        }
        else if(appName.equals("Mob Show")){
            rectq = new Rect(0,570,700,220);
            recta = new Rect(0,800,700,400);
            appResolution = new AppResolution();
            appResolution.setResolution(appName,rectq,recta);
            appResolution.getResolutionQ(appName);
            appResolution.getResolutionA(appName);

        }
        else if(appName.equals("Brain Baazi")){
            Rect rectq = new Rect(0,300,700,280);
            Rect recta = new Rect(0,535,700,450);
            appResolution = new AppResolution();
            appResolution.setResolution(appName,rectq,recta);
            appResolution.getResolutionQ(appName);
            appResolution.getResolutionA(appName);

        }
        else if(appName.equals("Play Prime")){
            Rect rectq = new Rect(0,350,700,215);
            Rect recta = new Rect(0,550,700,545);
            appResolution = new AppResolution();
            appResolution.setResolution(appName,rectq,recta);
            appResolution.getResolutionQ(appName);
            appResolution.getResolutionA(appName);
        }
        else if(appName.equals("HQTrivia")){
            Rect rectq = new Rect(0,220,700,350);
            Rect recta = new Rect(0,550,700,430);
            appResolution = new AppResolution();
            appResolution.setResolution(appName,rectq,recta);
            appResolution.getResolutionQ(appName);
            appResolution.getResolutionA(appName);
        }
        else if(appName.equals("JPlay")) {
            Rect rectq = new Rect(0, 700, 700, 210);
            Rect recta = new Rect(0, 900, 700, 360);
            appResolution = new AppResolution();
            appResolution.setResolution(appName, rectq, recta);
            appResolution.getResolutionQ(appName);
            appResolution.getResolutionA(appName);
        }
    }
}
