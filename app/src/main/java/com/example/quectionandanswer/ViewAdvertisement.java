package com.example.quectionandanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewAdvertisement extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_advertisement);

        String title= getIntent().getStringExtra("title");
        String name = getIntent().getStringExtra("name");
        String mobile = getIntent().getStringExtra("mobile");
        String email = getIntent().getStringExtra("email");
        String description = getIntent().getStringExtra("description");

        TextView txt_title = findViewById(R.id.txt_title);
        TextView txt_name = findViewById(R.id.txt_name);
        TextView txt_mobile = findViewById(R.id.txt_mobile);
        TextView txt_email = findViewById(R.id.txt_email);
        TextView txt_description = findViewById(R.id.txt_description);

        txt_title.setText(title);
        txt_name.setText(name);
        txt_mobile.setText(mobile);
        txt_email.setText(email);
        txt_description.setText(description);


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Advertisement").child(mobile);



    }
}