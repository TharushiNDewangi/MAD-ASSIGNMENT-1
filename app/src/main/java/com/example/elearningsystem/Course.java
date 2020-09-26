package com.example.elearningsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Course extends AppCompatActivity {

    static int GALARY = 1;
    EditText e1,e2;
    Spinner s;
    Button b1;
    ImageView i1;
    public Uri imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        b1=findViewById(R.id.add);
        e1=findViewById(R.id.CourseTitle);
        i1=findViewById(R.id.imageView);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalary();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addcourse();
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
    public void openGalary(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,GALARY);
        //onActivityResult();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALARY && resultCode==RESULT_OK && data!= null && data!=null)
        {
            imageUrl = data.getData();
            i1.setImageURI(imageUrl);
        }

    }
}