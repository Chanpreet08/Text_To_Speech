package com.example.cc.texttospeech;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private Button submit;
    private TextToSpeech textToSpeech;
    private String text = "";
    private FloatingActionButton fab;
    private String[] wordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText) findViewById(R.id.text);
        submit = (Button) findViewById(R.id.submit);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                wordList = getResources().getStringArray(R.array.word_array);
                builder.setTitle("Select a word").setItems(wordList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textToSpeech(wordList[which]);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = inputText.getText().toString();
                textToSpeech(text);
            }
        });

    }

    private void textToSpeech( final String selected)
    {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(Locale.US);
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
                    textToSpeech.speak(selected,TextToSpeech.QUEUE_FLUSH,null,null);
                else
                    textToSpeech.speak(selected,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }
}
