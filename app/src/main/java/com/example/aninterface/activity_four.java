package com.example.aninterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class activity_four extends AppCompatActivity {


    Button b7,b8,b9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        b7 = findViewById(R.id.back2);
        b8 = findViewById(R.id.update);
        b9 = findViewById(R.id.delete);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity_four.this, activity_two.class);
                startActivity(intent);
            }

        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_four.this, "updated succesfully", Toast.LENGTH_SHORT).show();
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_four.this, "deleted successfully", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(activity_four.this, activity_two.class);
                startActivity(intent2);
            }
        });
    }


    }
