package com.example.vaibhav.pti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import static com.example.vaibhav.pti.Login.MY_PREFS;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferences=getSharedPreferences(MY_PREFS,MODE_PRIVATE);
        boolean value = sharedPreferences.getBoolean("loginkey",false);

        if(!value) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, Login.class);
                    startActivity(i);
                    finish();
                }
            }, 2500);
        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i=new Intent(SplashScreen.this,Home.class);
                    startActivity(i);
                    finish();
                }
            }, 2500);
        }
    }
}
