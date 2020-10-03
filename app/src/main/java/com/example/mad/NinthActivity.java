package com.example.mad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class NinthActivity extends AppCompatActivity {
    BooksAdapter adapter;
    ArrayList<Book> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth);

        items = new ArrayList<>();
        final RecyclerView recyclerBook = findViewById(R.id.recyclerBook);
        adapter = new BooksAdapter(this, items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerBook.setLayoutManager(layoutManager);
        recyclerBook.setAdapter(adapter);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = db.getReference("bookstore");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {

                    Book adapter = dataSnapshot1.getValue(Book.class);
                    items.add(adapter);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}