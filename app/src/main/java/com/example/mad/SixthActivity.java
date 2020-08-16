package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SixthActivity extends AppCompatActivity {

    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        enter = findViewById(R.id.button);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent enterProfile = new Intent(SixthActivity.this,SecondActivity.class);
                startActivity(enterProfile);
            }
        });
    }
}