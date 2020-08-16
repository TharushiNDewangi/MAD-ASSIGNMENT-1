package com.example.aninterface;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class activity_three extends AppCompatActivity {


    Button b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        b3 = findViewById(R.id.login2);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_three.this, "you are successfully login", Toast.LENGTH_SHORT).show();

                Intent add = new Intent(activity_three.this, activity_one.class);

                startActivity(add);
            }
        });

    }

}