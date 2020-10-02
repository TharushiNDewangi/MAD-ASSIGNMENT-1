package com.example.quectionandanswer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addquestions extends AppCompatActivity {
    EditText etName,etQuestion;
    Button add;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestions);
        back = findViewById(R.id.button6);
        etQuestion=findViewById(R.id.txt_question);
        etName=findViewById(R.id.editTextTextPersonName10);
        add=findViewById(R.id.button5);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Q&A");
                String name=etName.getText().toString();
                String  question =etQuestion.getText().toString();

                QuestionsInfo questionClass = new QuestionsInfo(name,question);
                reference.child(name).setValue(questionClass);



              //  rootNode = FirebaseDatabase.getInstance().getReference("Q&A").child(hgfhf).child("answers").setValue();
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backTo = new Intent(Addquestions.this,SecondActivity.class);
                startActivity(backTo);
            }
        });

    }


    public void click(View view) {
        AlertDialog.Builder builder =new AlertDialog.Builder(Addquestions.this);
        builder.setTitle("Alert");
        builder.setMessage("Do you want to add Questions?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Addquestions.this,"Yes,Click",Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(
                        Addquestions.this,"No,Click",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Addquestions.this,"Cancel",Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}