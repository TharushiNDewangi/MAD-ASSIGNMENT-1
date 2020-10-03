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

public class FifthActivity extends AppCompatActivity {
    Button goAdd;
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        view = findViewById(R.id.btn2);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewDetail = new Intent(FifthActivity.this,SecondActivity.class);
                startActivity(viewDetail);
            }
        });

        goAdd = findViewById(R.id.button2);

        goAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ToAdd = new Intent(FifthActivity.this,EighthActivity.class);
                startActivity(ToAdd);
            }
        });



    }
}