package com.example.mad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    Button update;
    Button goBack;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        update = findViewById(R.id.btn8);
        goBack = findViewById(R.id.btn11);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateTo = new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(updateTo);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backTo = new Intent(ThirdActivity.this,FifthActivity.class);
                startActivity(backTo);
            }
        });
    }
}