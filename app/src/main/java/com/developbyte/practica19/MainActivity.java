package com.developbyte.practica19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.developbyte.practica19.Lista.ListaMasterViewController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start();
            }
        }, 3000);
    }

    private void start(){
        startActivity(new Intent(this, ListaMasterViewController.class));
        finish();
    }
}