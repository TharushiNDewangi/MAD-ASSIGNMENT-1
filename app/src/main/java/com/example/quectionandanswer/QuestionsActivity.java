package com.example.quectionandanswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    ArrayList<QuestionsInfo> list;
    QuestionsAdapter questionsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        list = new ArrayList<>();
        final RecyclerView recyclerQuestions = findViewById(R.id.recyclerQuestions);
        questionsAdapter = new QuestionsAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerQuestions.setLayoutManager(layoutManager);
        recyclerQuestions.setAdapter(questionsAdapter);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=db.getReference("Q&A");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    QuestionsInfo questionsInfo = dataSnapshot1.getValue(QuestionsInfo.class);
                    list.add(questionsInfo);
                }
                questionsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}