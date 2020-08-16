package com.example.elearningsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UpdateCourses extends AppCompatActivity {

    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_courses);
        b1 = findViewById(R.id.cancel);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnback();
            }
        });
        b2=findViewById(R.id.up1);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert(view);
            }
        });
        b3=findViewById(R.id.up2);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert(view);
            }
        });
        b4=findViewById(R.id.up3);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert(view);
            }
        });


    }

    public void returnback() {
        //Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();

        //call second activity
        Intent intent = new Intent(this, viewcourse.class);
        //

        startActivity(intent);

    }
    public void Alert(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        setTitle("Confirmation");
        alert.setMessage(" You wanted to update ?");
        alert.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(UpdateCourses.this,"updated",Toast.LENGTH_LONG).show();
                    }
                });

        alert.setNegativeButton("No",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
}