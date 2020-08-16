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

    Button goDelete;
    Button edit;
    Button back;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        goDelete= findViewById(R.id.btn1);
        edit = findViewById(R.id.btn2);
        goDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addDetail = new Intent(FifthActivity.this,FourthActivity.class);
                startActivity(addDetail);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editDetail = new Intent(FifthActivity.this,ThirdActivity.class);
                startActivity(editDetail);
            }
        });

        back = findViewById(R.id.button3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BackToLogin = new Intent(FifthActivity.this,MainActivity.class);
                startActivity(BackToLogin);
            }
        });

        add = findViewById(R.id.button2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ToAdd = new Intent(FifthActivity.this,EighthActivity.class);
                startActivity(ToAdd);
            }
        });
    }
}