package com.example.quectionandanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Advertisement extends AppCompatActivity {
    Button addAdvertisement;
    Button viewAdvertisement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);

        addAdvertisement =findViewById(R.id.button12);
        viewAdvertisement=findViewById(R.id.button13);

        addAdvertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAdv = new Intent(Advertisement.this,AddAdvertisement.class);
                startActivity(addAdv);
            }
        });
        viewAdvertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewAdv = new Intent(Advertisement.this,ViewAdvertisement.class);
                startActivity(viewAdv);
            }
        });

    }
}