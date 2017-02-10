package com.example.cc.texttospeech;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private Button submit;
    private TextToSpeech textToSpeech;
    private String text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText) findViewById(R.id.text);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = inputText.getText().toString();
                textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        textToSpeech.setLanguage(Locale.US);
                        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
                            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
                        else
                            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                    }
                });
            }
        });

    }
}
