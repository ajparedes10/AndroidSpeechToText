package com.example.andrea.speechtotext;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.String;


public class MainActivity extends AppCompatActivity{

    public static final int REQUEST_RECORD_PERMISSION = 100;
    VoiceFragment voiceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voiceFragment = new VoiceFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, voiceFragment).commit();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_RECORD_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    voiceFragment.recordSpeak();
                } else {
                    Toast.makeText(getApplicationContext(), "Tu dispositivo no soporta la función de text to speech", Toast.LENGTH_SHORT).show();

                }
            }

            // los otros casos pueden ser otros permisos que la palicación necesite verificar que tiene
        }
    }

}
