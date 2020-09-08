package com.example.pikachu.googleeye;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by PiKaChU on 13-May-18.
 */

public class Widget extends Service {

    WindowManager wm;
    LinearLayout ll;
    MainActivity main;
    static String selectedApp;
    int count;
    int firstindex;
    private AppSelected appSelected;
    private AppResolution appResolution;
    private TextView answer;
    private Button stop,screen;
    private Rect rectQues, rectAns;
    private ScreenImage screenImage;
    private CropImage cropImage;
    private ImageToText imageToText;
    private SearchEngine searchEngine;
    private ResolutionSet resolutionSet;
    private String webData2;
    private boolean answerboolean=false,alphaboolean = false;
    Bitmap quesImage;
    Bitmap ansImage;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        appResolution = new AppResolution();
        //appSelected = new AppSelected();
        resolutionSet = new ResolutionSet();
        searchEngine = new SearchEngine();
        imageToText = new ImageToText();
        cropImage = new CropImage();
        screenImage = new ScreenImage();

        selectedApp = AppSelected.selectedApp;
        Log.i("Widget","App: "+selectedApp.toString());
        resolutionSet.setResolution(selectedApp);
        rectQues = AppResolution.appresolutionq;
        Log.i("Widget","rectQues: "+rectQues);
        rectAns = AppResolution.appresolutiona;
        Log.i("Widget","rectAns: "+rectAns);

        wm = (WindowManager)getSystemService(WINDOW_SERVICE);

        ll = new LinearLayout(this);
        ll.setBackgroundColor(Color.BLACK);

        LinearLayout.LayoutParams layoutParameters = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,400);

        ll.setBackgroundColor(Color.argb(200,0,0,0));
        ll.setLayoutParams(layoutParameters);

        final LayoutParams parameters = new LayoutParams(
                600, 300, LayoutParams.TYPE_PHONE,
                LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        parameters.gravity = Gravity.CENTER | Gravity.CENTER;
        parameters.x = 0;
        parameters.y = 0;

        stop = new Button(this);
        screen = new Button(this);
        answer = new TextView(this);
        answer.setText(selectedApp);
        answer.setTextSize(14);
        stop.setText("S");
        screen.setText("C");

        ViewGroup.LayoutParams btnParameters = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        stop.setLayoutParams(btnParameters);

        ViewGroup.LayoutParams screenbtnParameters = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        screen.setLayoutParams(screenbtnParameters);

        ll.addView(answer);
        ll.addView(screen);
        ll.addView(stop);

        wm.addView(ll,parameters);

        ll.setOnTouchListener(new View.OnTouchListener() {
            LayoutParams updatedParameters = parameters;
            double x;
            double y;
            double pressedX;
            double pressedY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        x = updatedParameters.x;
                        y = updatedParameters.y;

                        pressedX = event.getRawX();
                        pressedY = event.getRawY();

                        break;

                    case MotionEvent.ACTION_MOVE:
                        updatedParameters.x = (int) (x + (event.getRawX() - pressedX));
                        updatedParameters.y = (int) (y + (event.getRawY() - pressedY));

                        wm.updateViewLayout(ll, updatedParameters);

                    default:
                        break;
                }

                return false;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wm.removeView(ll);
                stopSelf();
                System.exit(0);
            }
        });

        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap capturedImage = screenImage.getScreenImage();
                if(capturedImage != null){
                    quesImage = cropImage.getImage(capturedImage,rectQues);
                    ansImage = cropImage.getImage(capturedImage,rectAns);

                }
                String questiontext = imageToText.getImageText(quesImage,getBaseContext());
                Log.i("Widget","questiontext: "+questiontext);
                questiontext.replace("\n"," ");
                String answertext = imageToText.getImageText(ansImage,getBaseContext());
                Log.i("Widget","answertext: "+answertext);

                String[] answersplit = answertext.split("\n");
                String webData = searchEngine.getWebData(questiontext);
                if(webData == null){
                    Toast.makeText(getApplicationContext(),"WebData Null",Toast.LENGTH_SHORT).show();
                }
                Log.i("Widget","webData: "+webData);
                answer.setText("");

                for(String option:answersplit){
                    count = 0;
                    //withspacesearch(option,webData);
                    alphaboolean = true;
                    firstindex = webData.indexOf(option);
                    Log.i("Widget","Searching: "+option.toString());
                    if(firstindex > -1){
                        alphaboolean = false;
                        if(answer.getText() == selectedApp){
                            repeatedString(webData,option,firstindex);
                            Log.i("Widget","Answer: "+option.toString());
                            answer.setText( count+" "+option);
                        }
                        else {
                            Log.i("Widget","Answer: "+option.toString());
                            repeatedString(webData,option,firstindex);
                            answer.append(count + " " + option);
                        }
                        answer.append("\n");
                        Log.i("Widget: ","answer value: "+answer.getText().toString()+" : "+answer.getText().toString().equals(count+" .\n"));

                    }

                    if(alphaboolean) {
                        searchinLowerCase(option, webData);
                        if (answerboolean) {
                            if (answer.getText() == selectedApp) {
                                answer.setText(count + " " + option);
                            } else {
                                answer.append(count + " " + option);
                            }
                            answer.append("\n");
                        }
                    }
                }
                    // Searching in webData with lowerCase with both side space
                if(answer.getText().toString().equals(null) || answer.getText().toString().equals("")
                    || answer.getText().toString().equals(count+" .\n")){

                    Log.i("Widget","Searching in webData with lowerCase with both side space\n");

                    if(webData == null){
                        Toast.makeText(getApplicationContext(),"WebData Null",Toast.LENGTH_SHORT).show();
                    }
                    Log.i("Widget","webData: "+webData);
                    answer.setText("");

                    for(String option:answersplit){
                        option = option.replace(option," "+option+" ");
                        count = 0;
                        option=option.toLowerCase();
                        firstindex = webData.indexOf(option);
                        Log.i("Widget","Searching: "+option.toString());
                        if(firstindex > -1){

                            if(answer.getText() == selectedApp){
                                repeatedString(webData,option,firstindex);
                                Log.i("Widget","Answer: "+option.toString());
                                answer.setText( count+" "+option);
                            }
                            else {
                                Log.i("Widget","Answer: "+option.toString());
                                repeatedString(webData,option,firstindex);
                                answer.append(count + " " + option);
                            }
                            answer.append("\n");

                        }


                    }

                }
                    // Searching the with lowerCase option
                if(answer.getText().toString().equals(null) || answer.getText().toString().equals("")
                        || answer.getText().toString().equals(count+" .\n")){

                    Log.i("Widget","Searching the with lowerCase option\n");

                    if(webData == null){
                        Toast.makeText(getApplicationContext(),"WebData Null",Toast.LENGTH_SHORT).show();
                    }
                    Log.i("Widget","webData: "+webData);
                    answer.setText("");

                    for(String option:answersplit){
                        //option = option.replace(option," "+option+" ");
                        count = 0;
                        option=option.toLowerCase();
                        firstindex = webData.indexOf(option);
                        Log.i("Widget","Searching: "+option.toString());
                        if(firstindex > -1){

                            if(answer.getText() == selectedApp){
                                repeatedString(webData,option,firstindex);
                                Log.i("Widget","Answer: "+option.toString());
                                answer.setText( count+" "+option);
                            }
                            else {
                                Log.i("Widget","Answer: "+option.toString());
                                repeatedString(webData,option,firstindex);
                                answer.append(count + " " + option);
                            }
                            answer.append("\n");

                        }


                    }

                }


                // Search in webData2
                if(answer.getText().toString().equals("") || answer.getText().toString().equals(null)||
                        answer.getText().toString().equals(count+" .\n")){
                    getMoreData(questiontext);

                    Log.i("Widget","Search in webData2\n");

                    if(webData2 == null){
                        Toast.makeText(getApplicationContext(),"WebData2 Null",Toast.LENGTH_SHORT).show();
                    }
                    Log.i("Widget","webData2: "+webData2);
                    answer.setText("");

                    for(String option:answersplit){
                        option = option.replace(option," "+option+" ");
                        count = 0;
                        firstindex = webData2.indexOf(option);
                        Log.i("Widget","Searching: "+option.toString());
                        if(firstindex > -1){

                            if(answer.getText() == selectedApp){
                                repeatedString(webData2,option,firstindex);
                                Log.i("Widget","Answer: "+option.toString());
                                answer.setText( count+" "+option);
                            }
                            else {
                                Log.i("Widget","Answer: "+option.toString());
                                repeatedString(webData2,option,firstindex);
                                answer.append(count + " " + option);
                            }
                            answer.append("\n");

                        }


                    }
                }
                    // Search in webData2 using lowerCase
                if(answer.getText().toString().equals(null) || answer.getText().toString().equals("")
                        || answer.getText().toString().equals(count+" .\n")){

                    Log.i("Widget","Search in webData2 using lowerCase\n");

                    if(webData == null){
                        Toast.makeText(getApplicationContext(),"WebData2 Null",Toast.LENGTH_SHORT).show();
                    }
                    Log.i("Widget","webData2: "+webData2);
                    answer.setText("");

                    for(String option:answersplit){
                        count = 0;
                        option = option.replace(option," "+option+" ");
                        option=option.toLowerCase();
                        firstindex = webData2.indexOf(option);
                        Log.i("Widget","Searching: "+option.toString());
                        if(firstindex > -1){

                            if(answer.getText() == selectedApp){
                                repeatedString(webData2,option,firstindex);
                                Log.i("Widget","Answer: "+option.toString());
                                answer.setText( count+" "+option);
                            }
                            else {
                                Log.i("Widget","Answer: "+option.toString());
                                repeatedString(webData2,option,firstindex);
                                answer.append(count + " " + option);
                            }
                            answer.append("\n");

                        }


                    }

                }


                    //Search using firstSpaceINdex
                if(answer.getText().toString().equals("") || answer.getText().toString().equals("")
                        || answer.getText().toString().equals(count+" .\n")){

                    Log.i("Widget","Search using firstSpaceINdex\n");

                    for(String option:answersplit){
                        count = 0;
                        String newOption;
                        int firstspaceindex = option.indexOf(" ");
                        //int secondspaceindex = newOption.indexOf(" ");

                        if(firstspaceindex > -1){
                            newOption = option.substring(firstspaceindex);
                            firstindex = webData.indexOf(newOption);
                            Log.i("Widget","Searching: "+option.toString());
                            if(firstindex > -1){

                                if(answer.getText() == selectedApp){
                                    repeatedString(webData,newOption,firstindex);
                                    Log.i("Widget","Answer: "+newOption.toString());
                                    answer.setText( count+" "+option);
                                }
                                else {
                                    Log.i("Widget","Answer: "+option.toString());
                                    repeatedString(webData,newOption,firstindex);
                                    answer.append(count + " " + option);
                                }
                                answer.append("\n");

                            }
                        }

                    }

                }


                    // Search using 2 space index  from firstspaceindex to secondspaceindex
                if(answer.getText().toString().equals("") || answer.getText().toString().equals(null)
                        || answer.getText().toString().equals(count+" .\n")){

                    Log.i("Widget","Search using 2 space index  from firstspaceindex to secondspaceindex\n");

                    String newOption ;
                    for(String option: answersplit){
                        count = 0;
                        option = option.replace(",","");
                        int firstspaceIndex = option.indexOf(" ");
                        if(firstspaceIndex > -1) {
                            newOption = option.substring(firstspaceIndex+1);
                            int secondspaceIndex = newOption.indexOf(" ");
                            Log.i("Widget","newOption: "+newOption);
                            Log.i("Widget","secondspaceIndex: "+secondspaceIndex);
                            if(secondspaceIndex > -1 & newOption.length()>0) {
                                //newOption = newOption.substring(firstspaceIndex, (option.length()-newOption.length())+secondspaceIndex);
                                newOption = newOption.substring(0,secondspaceIndex);
                                Log.i("Widget","newOption: "+newOption);
                                int optionIndex = webData.indexOf(newOption);
                                if(optionIndex > -1){

                                    if(answer.getText() == selectedApp){
                                        repeatedString(webData,newOption,optionIndex);
                                        Log.i("Widget","Answer: "+option.toString());
                                        answer.setText( count+" "+option);
                                    }
                                    else {
                                        Log.i("Widget","Answer: "+option.toString());
                                        repeatedString(webData,newOption,optionIndex);
                                        answer.append(count + " " + option);
                                    }

                                    answer.append("\n");


                                }
                            }
                        }

                    }

                }
                 // Search before the firstspaceIndex
                if(answer.getText().toString().equals("") || answer.getText().toString().equals(null)
                        || answer.getText().toString().equals(count+" .\n")) {

                    Log.i("Widget","Search before the firstspaceIndex\n");

                    String newOption;
                    for (String option : answersplit) {
                        count = 0;
                        int firstspaceIndex = option.indexOf(" ");
                        if (firstspaceIndex > -1) {
                            newOption = option.substring(0, firstspaceIndex);
                            int optionIndex = webData.indexOf(newOption);
                            if (optionIndex > -1) {

                                if (answer.getText() == selectedApp) {
                                    repeatedString(webData, newOption, optionIndex);
                                    Log.i("Widget", "Answer: " + option.toString());
                                    answer.setText(count + " " + option);
                                } else {
                                    Log.i("Widget", "Answer: " + option.toString());
                                    repeatedString(webData, newOption, optionIndex);
                                    answer.append(count + " " + option);
                                }
                                answer.append("\n");
                            }

                        }


                    }


                }

                // Search after secondspaceIndex

                if(answer.getText().toString().equals("") || answer.getText().toString().equals(null)
                        || answer.getText().toString().equals(count+" .\n")){
                    String newOption;

                    Log.i("Widget","Search after secondspaceIndex\n");

                    for(String option: answersplit){
                        int firstspaceIndex = option.indexOf(" ");
                        Log.i("Widget","firstspaceIndex: "+firstspaceIndex);
                        newOption = option.substring(firstspaceIndex+1);
                        int secondspaceIndex = newOption.indexOf(" ");
                        //Log.i("Widget","secondspaceIndex: "+secondspaceIndex);
                       // Log.i("Widget","newOption length: "+newOption.length());

                        if(secondspaceIndex > -1){
                            newOption = option.substring((option.length() - newOption.length())+secondspaceIndex);
                            Log.i("Widget","newOption: "+newOption);
                            //newOption = newOption.substring((option.length() - newOption.length())+secondspaceindex)

                            int optionIndex = webData.indexOf(newOption);
                            if(optionIndex > -1){

                                if (answer.getText() == selectedApp) {
                                    repeatedString(webData, newOption, optionIndex);
                                    Log.i("Widget", "Answer: " + option.toString());
                                    answer.setText(count + " " + option);
                                } else {
                                    Log.i("Widget", "Answer: " + option.toString());
                                    repeatedString(webData, newOption, optionIndex);
                                    answer.append(count + " " + option);
                                }
                                answer.append("\n");

                            }
                        }
                    }
                }


                questiontext = questiontext.replace("\n"," ");
                Toast.makeText(getApplicationContext(),""+questiontext.toString(),Toast.LENGTH_SHORT).show();



        }

        });
    }

    public int repeatedString(String web, String searchstring, int findex){

        int secondindex = 0;
        int terminateindex = 0;
        String substring = web;

        while(terminateindex != findex){
            terminateindex = findex;
            substring = substring.substring(findex+searchstring.length(),substring.length()-1);
            secondindex = substring.indexOf(searchstring);
            if(secondindex != findex & !(secondindex<0)){
                findex = secondindex;
            }
            count+=1;
            Log.i("Widget","count: "+count+" "+findex);

        }

        return count;
    }

    public void getMoreData(String question){
        searchEngine = new SearchEngine();
        webData2= searchEngine.getWebData2(question);
    }

    public void withspacesearch(String option,String webthing){
        option = option.replace(option," "+option+" ");
        int charindex = webthing.indexOf(option);
        Log.i("Widget","charindex: "+charindex);
        if(charindex > -1){
            Log.i("Widget","Inside withperiodsearch");
            repeatedString(webthing,option,charindex);
        }
    }

    public boolean searchinLowerCase (String option,String webthing){
        answerboolean = false;
        boolean searchLower;
        String inLowerOption = option.toLowerCase();
        int answerIndex = webthing.indexOf(inLowerOption);
        if(answerIndex > -1){
            answerboolean = true;
            searchLower = true;
            repeatedString(webthing,inLowerOption,answerIndex);
        }
        else
            searchLower = false;
        return searchLower;
    }



}






































