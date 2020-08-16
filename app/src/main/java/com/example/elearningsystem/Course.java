package com.example.elearningsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Course extends AppCompatActivity {

    EditText e1,e2;
    Spinner s;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        b1=findViewById(R.id.add);
        e1=findViewById(R.id.CourseTitle);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcourse();
            }
        });
    }

    public void addcourse() {
        String text= e1.getText().toString();
        if(text.isEmpty())
        {
            alert("Please Insert Title");
        }

        Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();

        //call second activity
        Intent intent = new Intent(this,viewcourse.class);
        //

        startActivity(intent);

    }
    private void alert(String message)
    {
        AlertDialog dlg = new AlertDialog.Builder(Course.this).setTitle("Message").setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

}