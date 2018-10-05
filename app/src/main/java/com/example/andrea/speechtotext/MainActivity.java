package com.example.andrea.speechtotext;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.String;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int REQUEST_RECORD_PERMISSION = 100;
    VoiceFragment voiceFragment;
    private Button speakButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //boton principal
        speakButton =  findViewById(R.id.btnMic);
        speakButton.setOnClickListener(this);

        // crea el fragmento de voz
        voiceFragment = new VoiceFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, voiceFragment).commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            //verific si hay permiso para usar el microfono
            case REQUEST_RECORD_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    voiceFragment.recordSpeak();
                } else {
                    Toast.makeText(getApplicationContext(), "Tu dispositivo no permite la función de text to speech", Toast.LENGTH_SHORT).show();
                }
            }

            // los otros casos pueden ser otros permisos que la palicación necesite verificar que tiene
        }
    }
    /*
    onClick del boton principal
     */
    public void onClick(View v) {
        if (v.getId() == R.id.btnMic) {
            voiceFragment.recordSpeak();
        }
    }

}
