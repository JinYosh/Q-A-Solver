package com.example.pikachu.googleeye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private Button widgetButton,debugButton;
    private RadioButton r1,r2;
    private String searchEngine;
    private AppSelected appSelected;
    private StringEngine stringEngine;
    Widget widget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity","OnCreate");
        String[] strGames = {"Loco","Qureka","Mob Show","Brain Baazi","Play Prime","HQTrivia","JPlay"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,strGames);

        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoComplete);
        autoCompleteTextView.setThreshold(0);
        autoCompleteTextView.setAdapter(adapter);

        widgetButton = (Button)findViewById(R.id.buttonwidget);
        debugButton = (Button)findViewById(R.id.buttonDebug);


        r1 = (RadioButton)findViewById(R.id.radioButton);
        r2 = (RadioButton)findViewById(R.id.radioButton2);

        appSelected = new AppSelected();
        Log.i("MainActivity","Varaible Initialized");



    }

    public void RadioBing(View view){
        searchEngine = "https://www.bing.com/search?q=";
        Toast.makeText(this,"Bing", Toast.LENGTH_SHORT).show();
        Log.i("MainActivity","Bing Selected");
        if(r2.isChecked()){
            r2.setChecked(false);
        }
        stringEngine = new StringEngine();
        stringEngine.setEngine(searchEngine);

    }

    public void RadioGoogle(View view){
        searchEngine = "https://www.google.co.in/search?num=30&q=";
        Toast.makeText(this,"Google",Toast.LENGTH_SHORT).show();
        Log.i("MainActivity","Google Selected");
        if (r1.isChecked()){
            r1.setChecked(false);
        }
        stringEngine = new StringEngine();
        stringEngine.setEngine(searchEngine);
    }


    public void DebugButton(View view){

        Intent debugActivity = new Intent(MainActivity.this,DebugActivity.class);
        startActivity(debugActivity);
        Log.i("MainActivity","Activity Changed Debug");

    }


    public void WidgetButton(View view){
        String autoCompleteText = autoCompleteTextView.getText().toString();
        //Toast.makeText(this,autoCompleteText,Toast.LENGTH_SHORT).show();
        if(!autoCompleteText.equals(""))
        {
            Toast.makeText(this,autoCompleteText,Toast.LENGTH_SHORT).show();

            if(autoCompleteText.equals("Loco"))
            {
                appSelected.setappname("Loco");
                startService(new Intent(MainActivity.this,Widget.class));
                onBackPressed();
            }
            else if(autoCompleteText.equals("Qureka"))
            {
                appSelected.setappname("Qureka");
                startService(new Intent(MainActivity.this,Widget.class));
                onBackPressed();
            }
            else if(autoCompleteText.equals("Mob Show"))
            {
                appSelected.setappname("Mob Show");
                startService(new Intent(MainActivity.this,Widget.class));
                onBackPressed();
            }
            else if(autoCompleteText.equals("Brain Baazi"))
            {
                appSelected.setappname("Brain Baazi");
                startService(new Intent(MainActivity.this,Widget.class));
                onBackPressed();
            }

            else if(autoCompleteText.equals("Play Prime")){
                appSelected.setappname("Play Prime");
                startService(new Intent(MainActivity.this,Widget.class));
                onBackPressed();
            }
            else if(autoCompleteText.equals("HQTrivia")){
                appSelected.setappname("HQTrivia");
                startService(new Intent(MainActivity.this,Widget.class));
                onBackPressed();
            }
            else if(autoCompleteText.equals("JPlay")){
                appSelected.setappname("JPlay");
                startService(new Intent(MainActivity.this,Widget.class));
                onBackPressed();
            }
        }
        else
            Toast.makeText(this,"Select the App",Toast.LENGTH_SHORT).show();
    }



}
