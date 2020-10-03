package com.example.elearningsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class showresult extends AppCompatActivity {

    TextView t1,t2,t3;
    String res,avg,name;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showresult);


        t1=findViewById(R.id.res);
        t2=findViewById(R.id.avg);
        t3=findViewById(R.id.name);

        Intent intent=getIntent();

        res =intent.getStringExtra("res");
        avg =intent.getStringExtra("avg");
        name=intent.getStringExtra("name");

        t1.setText("mark percentage : "+res);
        t2.setText("Average mark : "+avg);

        float castedavg=Float.parseFloat(avg);

        if(castedavg >= 95)
        {
            t3.setText(name+" well done ");
        }
        else if(castedavg >= 75)
        {
            t3.setText(name+" you are best, try to get 95%");
        }
        else if(castedavg >= 65)
        {
            t3.setText(name+" you are Average, Try to get 75%");
        }
        else if(castedavg >= 55)
        {
            t3.setText(name+" you are good, Try to get 65%");
        }
        else if(castedavg >= 45)
        {
            t3.setText(name+"  Try to get 55%");
        }
        else
        {
            t3.setText(name+" you are week but i know you can. try to do best !");
        }

    }


}