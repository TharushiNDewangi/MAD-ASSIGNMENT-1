package com.example.quectionandanswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Editquestions extends AppCompatActivity {
    DatabaseReference databaseReference;
    Button btnEdit;
    String name, question;
    EditText txt_question, txt_answer1, txt_answer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editquestions);
        btnEdit = findViewById(R.id.button5);
        txt_question = findViewById(R.id.txt_question);
        txt_answer1 = findViewById(R.id.txt_answer1);
        txt_answer2 = findViewById(R.id.txt_answer2);
        name = getIntent().getStringExtra("name");
        question = getIntent().getStringExtra("question");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Q&A").child(name).child("answers");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Answer answer = dataSnapshot.getValue(Answer.class);
                txt_question.setText(question);
                txt_answer1.setText(answer.getAnswer1());
                txt_answer2.setText(answer.getAnswer2());
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer1 = txt_answer1.getText().toString();
                String answer2 = txt_answer2.getText().toString();
                String updatedQuestion = txt_question.getText().toString();
                updateQuestion(updatedQuestion);
                updateAnswer(answer1, answer2);
            }
        });


        //FirebaseDatabase db = FirebaseDatabase.getInstance();
        //databaseReference= db.getReference("Q&A").child(name);
    }

//    private void editData(String strTitle, final String strBody){
//        Query editQuery = databaseReference.orderByChild("title").equalTo(strTitle);
//        editQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot edtData: dataSnapshot.getChildren()){
//                    edtData.getRef().child("content").setValue(strBody);
//                }
//                Toast.makeText(Editquestions.this,"Data Edited",Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(Editquestions.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    //update data
    private void updateQuestion(String question) {
        //getting the specified  reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Q&A").child(name).child("question");
        dR.setValue(question);
        Toast.makeText(getApplicationContext(), " Updated", Toast.LENGTH_LONG).show();
    }

    private void updateAnswer(String answer1, String answer2) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Q&A").child(name).child("answers");

        //updating answer
        Answer answer = new Answer(answer1, answer2);
        dR.setValue(answer);
        Toast.makeText(getApplicationContext(), " Updated", Toast.LENGTH_LONG).show();
    }


    public void click(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Editquestions.this);
        builder.setTitle("Alert");
        builder.setMessage("Do You Want to Edit your questions?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Editquestions.this, "Yes click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Editquestions.this, "No Click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Editquestions.this, "cancel", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}