package com.example.quectionandanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Button add;
    Button edit;
    Button delete;
    Button view;
    Button back;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
context = this;
        try {
            add = findViewById(R.id.button2);
            edit = findViewById(R.id.button3);
            delete = findViewById(R.id.button4);
            view = findViewById(R.id.button9);
            back = findViewById(R.id.button15);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent backTo = new Intent(context, MainActivity.class);
                    startActivity(backTo);
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent addQue = new Intent(context, Addquestions.class);
                    startActivity(addQue);
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editQue = new Intent(context, Editquestions.class);
                    startActivity(editQue);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent deleteQue = new Intent(context, Deletequestions.class);
                    startActivity(deleteQue);
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent viewQue = new Intent(context, QuestionsActivity.class);
                    startActivity(viewQue);
                }
            });

        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}