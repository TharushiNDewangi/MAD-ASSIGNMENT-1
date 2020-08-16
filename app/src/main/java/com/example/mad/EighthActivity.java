package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EighthActivity extends AppCompatActivity {

    Button goProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth);

        goProfile = findViewById(R.id.button5);

        goProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backProfile = new Intent(EighthActivity.this,FifthActivity.class);
                startActivity(backProfile);
            }
        });

    }

    public void Click(View view) {

            AlertDialog.Builder builder= new AlertDialog.Builder(EighthActivity.this);
            builder.setTitle("Confirmation");
            builder.setMessage("Do You Want to Save Your Profile?");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Toast.makeText(EighthActivity.this, "Yes Click", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Toast.makeText(EighthActivity.this, "No Click", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialogInterface = builder.create();
            dialogInterface.show();
    }
}