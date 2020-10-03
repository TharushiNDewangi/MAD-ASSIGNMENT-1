package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button goProfile, BookStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goProfile = findViewById(R.id.button);

        goProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FifthActivity.class);
                startActivity(intent);
            }
        });

        BookStore = findViewById(R.id.button5);

        BookStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goCart = new Intent(MainActivity.this,FourthActivity.class);
                startActivity(goCart);
            }
        });

    }
}