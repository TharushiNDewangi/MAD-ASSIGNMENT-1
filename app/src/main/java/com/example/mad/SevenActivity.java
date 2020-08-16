package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SevenActivity extends AppCompatActivity {

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);

        register = findViewById(R.id.btn17);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ToRegister = new Intent(SevenActivity.this, FifthActivity.class);
                startActivity(ToRegister);
            }
        });
    }
}