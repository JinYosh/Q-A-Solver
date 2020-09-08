package com.example.pikachu.googleeye;

import android.graphics.Rect;

/**
 * Created by PiKaChU on 13-May-18.
 */

public class AppResolution {
    public static Rect LocoRectQ,QurekaRectQ,MobShowRectQ,BrainBaaziRectQ,PlayPrimeQ,HQTriciaQ,JPlayQ;
    public static Rect LocoRectA,QurekaRectA,MobShowRectA,BrainBaaziRectA,PlayPrimeA,HQTriviaA,JPlayA;
    public static Rect appresolutionq,appresolutiona;

    public void setResolution(String appName,Rect coordinateq, Rect coordinatea){
        if (appName.equals("Loco")){
            LocoRectQ = coordinateq;
            LocoRectA = coordinatea;
        }

        else if (appName.equals("Qureka")){
            QurekaRectQ = coordinateq;
            QurekaRectA = coordinatea;
        }

        else if(appName.equals("Mob Show")){
            MobShowRectQ = coordinateq;
            MobShowRectA = coordinatea;
        }

        else if(appName.equals("Brain Baazi")){
            BrainBaaziRectQ = coordinateq;
            BrainBaaziRectA = coordinatea;
        }

        else if(appName.equals("Play Prime")){
            PlayPrimeQ = coordinateq;
            PlayPrimeA = coordinatea;
        }
        else if(appName.equals("HQTrivia")){
            HQTriciaQ = coordinateq;
            HQTriviaA = coordinatea;
        }
        else if(appName.equals("JPlay")){
            JPlayQ = coordinateq;
            JPlayA = coordinatea;
        }
    }

    public Rect getResolutionQ(String appName){


        if (appName.equals("Loco")){

            appresolutionq = LocoRectQ;

        }
        else if(appName.equals("Qureka")){
            appresolutionq = QurekaRectQ;
        }
        else if(appName.equals("Mob Show")){
            appresolutionq = MobShowRectQ;
        }
        else if(appName.equals("Brain Baazi")){
            appresolutionq = BrainBaaziRectQ;
        }
        else if(appName.equals("Play Prime")){
            appresolutionq = PlayPrimeQ;
        }
        else if(appName.equals("HQTrivia")){
            appresolutionq = HQTriciaQ;
        }
        else if(appName.equals("JPlay")) {
            appresolutionq = JPlayQ;
        }

        return appresolutionq;

    }

    public Rect getResolutionA(String appName){


        if (appName.equals("Loco")){

            appresolutiona = LocoRectA;

        }
        else if(appName.equals("Qureka")){
            appresolutiona = QurekaRectA;
        }
        else if(appName.equals("Mob Show")){
            appresolutiona = MobShowRectA;
        }
        else if(appName.equals("Brain Baazi")){
            appresolutiona = BrainBaaziRectA;
        }
        else if(appName.equals("Play Prime")){
            appresolutiona = PlayPrimeA;
        }
        else if(appName.equals("HQTrivia")){
            appresolutiona = HQTriviaA;
        }
        else if(appName.equals("JPlay")){
            appresolutiona = JPlayA;
        }

        return appresolutiona;

    }
}
