package com.example.mad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThirdActivity extends AppCompatActivity {
    DatabaseReference databaseReference;

    Button update;
    String name;
    String username;
    String course;
    String phone;
   String password;
   EditText Fname,userName,Course,userMobile,userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        update = findViewById(R.id.btn8);
        Fname = findViewById(R.id.Name);
        userName = findViewById(R.id.user);
        Course= findViewById(R.id.user_user_course);
        userMobile = findViewById(R.id.user_Mobile);
        userPassword = findViewById(R.id.editTextTextPassword3);
        username = getIntent().getStringExtra("username");
        course = getIntent().getStringExtra("course");
        phone = getIntent().getStringExtra("phone");

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= db.getReference("profile").child(username);
        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProfileHelper profileHelper = snapshot.getValue(ProfileHelper.class);
                Fname.setText(profileHelper.getName());
                userPassword.setText(profileHelper.getPassword());
                userName.setText(username);
                userMobile.setText(phone);
                Course.setText(course);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_Mobile = userMobile.getText().toString();
                String U_Course = Course.getText().toString();
                updateProfile(user_Mobile,U_Course);
            }
        });

    }

    private void updateProfile(String user_mobile, String u_course) {
       DatabaseReference dR = FirebaseDatabase.getInstance().getReference("profile").child(username).child("phone");
       dR.setValue(user_mobile);
       DatabaseReference dR1 = FirebaseDatabase.getInstance().getReference("profile").child(username).child("course");
       dR1.setValue(u_course);

      Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
    }

}