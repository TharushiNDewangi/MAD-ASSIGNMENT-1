package com.example.aninterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class activity_one extends AppCompatActivity {


        Button button4,button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

       button4= findViewById(R.id.add2);
       button5=findViewById(R.id.back);
       button4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(activity_one.this, "your news added", Toast.LENGTH_SHORT).show();
               Intent add = new Intent(activity_one.this, activity_two.class);
               startActivity(add);
           }
       });
       button5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent add2 = new Intent(activity_one.this,activity_two.class);
               startActivity(add2);
           }
       });

    }


}