package com.example.elearningsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class viewcourse extends AppCompatActivity {

    Button b1,b2;
    EditText e1,e2;
    ImageView i1;

    String title;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcourse);

        b1=findViewById(R.id.up1);
        b2=findViewById(R.id.up2);

        e1=findViewById(R.id.viewtitle);
        e2=findViewById(R.id.description);

        i1=findViewById(R.id.imageView);


        title=getIntent().getStringExtra("title");
        Toast.makeText(viewcourse.this,title,Toast.LENGTH_LONG).show();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatecourse();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert(view);deletecourse();
            }
        });
        getdbvalues(title);
    }

    public void getdbvalues(final String title)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("course").child(title);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                   String title = snapshot.child("title").getValue().toString();
                   String des  = snapshot.child("des").getValue().toString();
                  String img  = snapshot.child("mimagurl").getValue().toString();
                  Picasso.get().load(img).into(i1);
                   e1.setText(title);
                   e2.setText(des);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updatecourse() {
        //Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

        //call second activity
        Intent intent = new Intent(this, UpdateCourses.class);
        //

        startActivity(intent);

    }

    public void deletecourse() {
        //Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();

        //call second activity

        Intent intent = new Intent(this, DeleteCourses.class);
        //

        startActivity(intent);

    }
    public void Alert(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alert!");
        alert.setMessage(" You clicked delete");
        alert.setCancelable(true);
        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Delete button clicked",Toast.LENGTH_LONG).show();
                    }
                });

        alert.show();



    }
}