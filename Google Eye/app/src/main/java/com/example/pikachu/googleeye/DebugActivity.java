package com.example.pikachu.googleeye;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DebugActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private Button screenButton,textButton,prevButton;
    private TextView questionText, optionText, filename;
    private ImageView questionImage, optionImage;

    private String appNmae;
    private AppResolution appResolution;
    private ScreenImage screenImage;
    private CropImage cropImage;
    private ImageToText imageToText;
    private ResolutionSet resolutionSet;
    private FileList fileList;
    List<String> filenames;
    String name;
    int serialnum;
    private GrabImage grabImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        appResolution = new AppResolution();
        //appSelected = new AppSelected();
        resolutionSet = new ResolutionSet();
        imageToText = new ImageToText();
        cropImage = new CropImage();
        screenImage = new ScreenImage();
        fileList = new FileList();
        grabImage = new GrabImage();
        name = null;
        serialnum = 0;

        String[] strGames = {"Loco","Qureka","Mob Show","Brain Baazi","Play Prime","HQTrivia","JPlay"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,strGames);

        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoComplete);
        autoCompleteTextView.setThreshold(0);
        autoCompleteTextView.setAdapter(adapter);

        screenButton = (Button)findViewById(R.id.button2);
        textButton = (Button)findViewById(R.id.button3);
        prevButton = (Button)findViewById(R.id.button4);


        questionText = (TextView)findViewById(R.id.textView);
        optionText = (TextView)findViewById(R.id.textView2);
        filename = (TextView)findViewById(R.id.textView3);

        questionImage = (ImageView)findViewById(R.id.imageView2);
        optionImage = (ImageView)findViewById(R.id.imageView3);

        appResolution = new AppResolution();
        screenImage = new ScreenImage();
        cropImage = new CropImage();
        imageToText = new ImageToText();

        filenames = new ArrayList<String>();



        screenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                appNmae = autoCompleteTextView.getText().toString();
                if(appNmae != null){
                    resolutionSet.setResolution(appNmae);
                    Rect rectQ = appResolution.getResolutionQ(appNmae);
                    Rect rectA = appResolution.getResolutionA(appNmae);
                    if(filenames == null) {
                        filenames = fileList.getList(appNmae);
                        Log.i("DebugActivity","List: "+filenames);
                    }

                    String name = null;

                    name = filenames.get(serialnum);
                    serialnum++;

                    if(serialnum >= filenames.size()){
                        serialnum=0;
                        Log.i("DebugActivity","serialnum"+serialnum);
                        Toast.makeText(getApplicationContext(),"serialnum: "+serialnum,Toast.LENGTH_SHORT).show();
                    }

                    filename.setText(filtername(name));

                    Bitmap bitmap = grabImage.getImage(name);
                    Bitmap cropped = cropImage.getImage(bitmap,rectQ);
                    Log.i("DebugActivity","serialnum"+serialnum);

                    if(cropped != null){
                        questionImage.setImageBitmap(cropped);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Ques Image Nul",Toast.LENGTH_SHORT).show();

                    cropped = cropImage.getImage(bitmap,rectA);
                    if (cropped != null){
                        optionImage.setImageBitmap(cropped);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Opt Image null",Toast.LENGTH_SHORT).show();


                }
                else
                    Toast.makeText(getApplicationContext(),"Select the app",Toast.LENGTH_SHORT).show();
            }
        });

        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("DebugActivity","Getting text");
                Bitmap imageBitmap = ((BitmapDrawable)questionImage.getDrawable()).getBitmap();
                String text = imageToText.getImageText(imageBitmap,getApplicationContext());
                questionText.setText(text);
                Log.i("DebugActivity","Question: "+text);

                imageBitmap = ((BitmapDrawable)optionImage.getDrawable()).getBitmap();
                text = imageToText.getImageText(imageBitmap,getApplicationContext());
                optionText.setText(text);
                Log.i("DebugActivity","Options: "+text);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appNmae = autoCompleteTextView.getText().toString();
                if(appNmae != null){
                    resolutionSet.setResolution(appNmae);
                    Rect rectQ = appResolution.getResolutionQ(appNmae);
                    Rect rectA = appResolution.getResolutionA(appNmae);
                    Log.i("DebugActivity: ","rectQ: "+rectQ);
                    Log.i("DebugActivity: ","rectA: "+rectA);
                    if(filenames == null) {
                        filenames = fileList.getList(appNmae);
                        Log.i("DebugActivity","List: "+filenames);
                    }


                    String name = null;

                    name = filenames.get(serialnum);
                    serialnum--;
                    if(serialnum < 0){
                        serialnum=filenames.size()-1;
                        Log.i("DebugActivity","serialnum"+serialnum);
                        Toast.makeText(getApplicationContext(),"serialnum: "+serialnum,Toast.LENGTH_SHORT).show();
                    }

                    filename.setText(filtername(name));

                    Bitmap bitmap = grabImage.getImage(name);
                    Bitmap cropped = cropImage.getImage(bitmap,rectQ);
                    Log.i("DebugActivity","serialnum"+serialnum);

                    if(cropped != null){
                        questionImage.setImageBitmap(cropped);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Ques Image Nul",Toast.LENGTH_SHORT).show();

                    cropped = cropImage.getImage(bitmap,rectA);
                    if (cropped != null){
                        optionImage.setImageBitmap(cropped);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Opt Image null",Toast.LENGTH_SHORT).show();


                }
                else
                    Toast.makeText(getApplicationContext(),"Select the app",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public String filtername (String filelocation){

        int firstindex,secondindex,terminateindex = -1;
        firstindex = filelocation.indexOf("/");
       /* while(terminateindex != firstindex){
            terminateindex = firstindex;
            location = location.substring(firstindex+1,location.length() -1);
            secondindex = location.indexOf("/");
            firstindex = secondindex;

        } */

        int lastindex=0;
        Log.i("DebugActivity","lastindex Checking ");
        for(int i=0; i<filelocation.length(); i++){
            if(filelocation.substring(i,i+1).equals("/")){

                lastindex =i;
                Log.i("DebugActivity","lastindex: "+lastindex);

            }
        }
        filelocation = filelocation.substring(lastindex+1,filelocation.length());

        return filelocation;
    }

    public void getList(View view){
        if(!autoCompleteTextView.toString().equals("")) {
            filenames = fileList.getList(autoCompleteTextView.getText().toString());
        }
        else
            Toast.makeText(getApplicationContext(),"Select the App",Toast.LENGTH_SHORT).show();
        Log.i("DebugActivity","filenames: "+filenames.toString().replace(",","\n"));
    }
}
