package com.example.pikachu.googleeye;

/**
 * Created by PiKaChU on 14-May-18.
 */

public class StringEngine {
    static String search;
    public void setEngine(String engine){

        search = engine;
    }

    public String getEngine(){
        if(search == null){
            search = "https://www.bing.com/search?q=";
        }
        return search;
    }
}
