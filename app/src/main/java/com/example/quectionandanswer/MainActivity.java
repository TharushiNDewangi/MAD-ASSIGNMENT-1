package com.example.quectionandanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button queSession;
    Button advertisement;
    Button math;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queSession = findViewById(R.id.button10);
        advertisement =findViewById(R.id.button11);
        math=findViewById(R.id.btnmath);

        queSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addQue = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(addQue);
            }
        });
        advertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adv = new Intent(MainActivity.this, AdvertisementActivity.class);
                startActivity(adv);
            }
        });
        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mth = new Intent(MainActivity.this, MathTable.class);
                startActivity(mth);
            }
        });





    }

    }