package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewProfile extends AppCompatActivity {
    DatabaseReference databaseReference;
    Button go_Edit_Page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        String name = getIntent().getStringExtra("name");
        final String username = getIntent().getStringExtra("username");
        final String course = getIntent().getStringExtra("course");
        final String phone = getIntent().getStringExtra("phone");
        String password = getIntent().getStringExtra("password");

        TextView fullName = findViewById(R.id.Txt_fullname);
        TextView Username = findViewById(R.id.Txt_username);
        TextView U_password= findViewById(R.id.Txt_password);
        TextView U_course = findViewById(R.id.Txt_course);
        TextView U_mobile = findViewById(R.id.Txt_phone);

        fullName.setText(name);
        Username.setText(username);
        U_course.setText(course);
        U_mobile.setText(phone);
        U_password.setText(password);

        FirebaseDatabase db = FirebaseDatabase.getInstance();


        go_Edit_Page = findViewById(R.id.btnUpdate);

        go_Edit_Page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProfile.this,ThirdActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("course",course);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });

    }
}