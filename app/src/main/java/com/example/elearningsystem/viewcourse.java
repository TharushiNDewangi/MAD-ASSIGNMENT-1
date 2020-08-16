package com.example.elearningsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class viewcourse extends AppCompatActivity {

    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcourse);

        b1=findViewById(R.id.up1);
        b2=findViewById(R.id.up2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatecourse();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert(view);deletecourse();
            }
        });
    }

    public void updatecourse() {
        //Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

        //call second activity
        Intent intent = new Intent(this, UpdateCourses.class);
        //

        startActivity(intent);

    }

    public void deletecourse() {
        //Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

        //call second activity

        Intent intent = new Intent(this, DeleteCourses.class);
        //

        startActivity(intent);

    }
    public void Alert(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alert!");
        alert.setMessage(" You clicked delete");
        alert.setCancelable(true);
        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Delete button clicked",Toast.LENGTH_LONG).show();
                    }
                });

        alert.show();



    }
}