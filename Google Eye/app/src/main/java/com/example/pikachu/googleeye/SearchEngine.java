package com.example.pikachu.googleeye;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by PiKaChU on 14-May-18.
 */

public class SearchEngine {

    String webSearch;
    StringBuilder webData,webData2;
    URL url;
    String finalquestion;
    private StringEngine stringEngine;
    private HttpURLConnection connection;

    public String getWebData(String question){
        stringEngine = new StringEngine();
        webSearch = StringEngine.search;
        webData = new StringBuilder();
        question = question.replace(":","");
        question = question.replace(",","");
        question = question.replace("/"," ");
        question = question.replace("."," ");
        question = question.replace("_","");
        question = question.replace("\n"," ");
        question = question.replace("Eliminated"," ");
        question = question.replace("ELIMINATED"," ");
        question = question.replace(" NOT "," ");
        question = question.replace(" not "," ");
        question = question.replace("'","");
        question = question.replace("\"","");
        question = question.replace("?","?+");
        question = question.replace(" ","+");
        if (question.endsWith("+")){
            question = question.substring(0,question.length()-1);
        }
        finalquestion = question;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    url = new URL(webSearch+finalquestion.toString());
                    Log.i("SearchEngine","URL: "+url.toString());
                } catch (Exception e) {
                    //e.printStackTrace();
                }

                int response = 0;

                try {
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
                    connection.setRequestProperty("Accept","text/html");
                    response = connection.getResponseCode();

                    if(response == 200){
                        proceedForData(connection);
                    }

                    else if(response != 200){
                        try {
                            URL binguri = new URL("https://www.bing.com/search?q=" + finalquestion);
                            connection = (HttpURLConnection) binguri.openConnection();
                            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
                            Log.i("Response: ", String.valueOf(response));
                            //connection.setRequestProperty("Accept","text/html");
                            System.out.print("Response: " + response);
                            response = connection.getResponseCode();
                        } catch (Exception e) {
                            Log.i("Bing: ", "Search Error");
                        }
                        if (response == 200) {
                            proceedForData(connection);
                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        try {
            thread.join();
            thread.setPriority(Thread.NORM_PRIORITY);
        } catch (Exception e) {
            Log.i("Search Engine","Thread Join failed");
        }
        if (webData==null){
            webData.append("null");
        }
        return webData.toString();
    }

    public String getWebData2(String question){
        stringEngine = new StringEngine();
        webSearch = StringEngine.search;
        webData = new StringBuilder();
        question = question.replace(":","");
        question = question.replace(",","");
        question = question.replace("/"," ");
        question = question.replace("."," ");
        question = question.replace("_","");
        question = question.replace("&","");
        question = question.replace("\n"," ");
        question = question.replace("Eliminated","");
        question = question.replace("ELIMINATED","");
        question = question.replace(" NOT "," ");
        question = question.replace(" not "," ");
        question = question.replace("'","");
        question = question.replace("\"","");
        question = question.replace("?","?+");
        question = question.replace(" ","+");
        if (question.endsWith("+")){
            question = question.substring(0,question.length()-1);
        }
        finalquestion = question;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(webSearch.equals("https://www.google.co.in/search?q=")) {
                        url = new URL(webSearch + finalquestion.toString() + "&start=10");
                    }
                    else if(webSearch.equals("https://www.bing.com/search?q=")){
                        url = new URL(webSearch + finalquestion.toString() + "&first=9");
                    }
                    Log.i("SearchEngine","URL: "+url.toString());
                } catch (Exception e) {
                    //e.printStackTrace();
                }

                int response = 0;

                try {
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
                    connection.setRequestProperty("Accept","text/html");
                    response = connection.getResponseCode();

                    if(response == 200){
                        proceedForData(connection);
                    }

                    else if(response != 200){
                        try {
                            URL binguri = new URL("https://www.bing.com/search?q=" + finalquestion+"&first=9");
                            connection = (HttpURLConnection) binguri.openConnection();
                            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
                            Log.i("Response: ", String.valueOf(response));
                            //connection.setRequestProperty("Accept","text/html");
                            System.out.print("Response: " + response);
                            response = connection.getResponseCode();
                        } catch (Exception e) {
                            Log.i("Bing: ", "Search Error");
                        }
                        if (response == 200) {
                            proceedForData(connection);
                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        try {
            thread.join();
            thread.setPriority(Thread.NORM_PRIORITY);
        } catch (Exception e) {
            Log.i("Search Engine","Thread Join failed");
        }
        if (webData==null){
            webData.append("null");
        }
        return webData.toString();
    }


    public void proceedForData(HttpURLConnection connection){

        BufferedReader in = null;
        Log.i("SearchEngine","proceedForData inside");
        try {
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
        } catch (Exception e) {
            Log.v("Error", "BufferedReader");
            //e.printStackTrace();
        }
        String inputLine;
        try {
            while ((inputLine = in.readLine()) != null) {
                webData.append(inputLine);
                //Log.i("proceedData: ",""+webData);
            }
        } catch (Exception e) {
            Log.v("Error", "Data Input");
            //e.printStackTrace();
        }
        try {
            in.close();
        } catch (Exception e) {
            Log.v("Error", "IN not close");
            //e.printStackTrace();
        }

    }
}
