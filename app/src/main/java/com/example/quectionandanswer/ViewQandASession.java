package com.example.quectionandanswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewQandASession extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText txt_answer1,txt_answer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_qand_a_session);

        String name = getIntent().getStringExtra("name");
        String question = getIntent().getStringExtra("question");

        TextView txt_name = findViewById(R.id.txt_mobile);
        TextView txt_question = findViewById(R.id.txt_question);
        txt_answer1 = findViewById(R.id.txt_answer1);
        txt_answer2 = findViewById(R.id.txt_answer2);
        Button btn_add = findViewById(R.id.btn_add);
        txt_name.setText(name);
        txt_question.setText(question);


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Q&A").child(name).child("answers");

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Answer answer = new Answer(txt_answer1.getText().toString(), txt_answer2.getText().toString());
                databaseReference.setValue(answer).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ViewQandASession.this, "Answers Added Successfully", Toast.LENGTH_SHORT).show();
                        txt_answer1.setText("");
                        txt_answer2.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
    }
}