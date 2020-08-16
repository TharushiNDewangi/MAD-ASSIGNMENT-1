package com.example.quectionandanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button add;
    Button edit;
    Button delete;
    Button view;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        add = findViewById(R.id.button2);
        edit =findViewById(R.id.button3);
        delete =findViewById(R.id.button4);
        view = findViewById(R.id.button9);
        back=findViewById(R.id.button15);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backTo = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(backTo);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addQue = new Intent(SecondActivity.this,Addquestions.class);
                startActivity(addQue);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editQue =new Intent(SecondActivity.this,Editquestions.class);
                startActivity(editQue);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deleteQue = new Intent(SecondActivity.this,Deletequestions.class);
                startActivity(deleteQue);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewQue = new Intent(SecondActivity.this,ViewQandASession.class);
                startActivity(viewQue);
            }
        });



    }

}