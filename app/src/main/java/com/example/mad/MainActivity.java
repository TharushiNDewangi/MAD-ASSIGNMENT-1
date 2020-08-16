package com.example.mad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button newMem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btn13);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editDetail = new Intent(MainActivity.this,SixthActivity.class);
                startActivity(editDetail);
            }
        });

        newMem= findViewById(R.id.btn12);

        newMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AsMem = new Intent(MainActivity.this,SevenActivity.class);
                startActivity(AsMem);
            }
        });



    }
}