package com.example.elearningsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class UpdateCourses extends AppCompatActivity {

    Button b1,b4;
    EditText e1,e2;
    ImageView i1;
    static int GALARY = 1;
    String titl;
    DatabaseReference dbreference;
    String TITLE,DESCRIPTION;
    String title,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_courses);
        dbreference=FirebaseDatabase.getInstance().getReference("course");

        e1 = findViewById(R.id.viewtitle);
        e2 = findViewById(R.id.description);
        b1 = findViewById(R.id.cancel);
        i1 = findViewById(R.id.imageView);


        title = getIntent().getExtras().getString("title");
        des = getIntent().getExtras().getString("des");

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalary();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnback();
            }
        });
        titl=getIntent().getStringExtra("title");

        b4=findViewById(R.id.up3);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert(view);
                //update();
            }
        });
        viewvalues();
        getdbvalues(titl);

    }

    public void returnback() {
        //Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();

        //call second activity
        Intent intent = new Intent(this, viewcourse.class);
        //

        startActivity(intent);

    }
    public void Alert(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        setTitle("Confirmation");
        alert.setMessage(" You wanted to update ?");
        alert.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if(titlechanged() || deschanged())
                        {
                            Toast.makeText(getApplicationContext(),"successfully updad",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"not updad",Toast.LENGTH_LONG).show();
                        }
                    }
                });

        alert.setNegativeButton("No",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
    public void viewvalues()
    {


        e1.setText(title);
        e2.setText(des);
        //i1.setImageResource(Integer.parseInt(img));

    }

    public void getdbvalues(final String title)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("course").child(title);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String img  = snapshot.child("mimagurl").getValue().toString();
                Picasso.get().load(img).into(i1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void update(View view)
     {
         if(titlechanged() || deschanged())
         {
             Toast.makeText(getApplicationContext(),"successfully updad",Toast.LENGTH_LONG).show();
         }
         else
         {
             Toast.makeText(getApplicationContext(),"not updad",Toast.LENGTH_LONG).show();
         }
//        dbreference=FirebaseDatabase.getInstance().getReference("course");
//        dbreference.child("course").child("std1").child("name").setValue(e1.getText().toString().trim());
//        dbreference.child("Student/std1/address").setValue(e2.getText().toString().trim());

    }
    public void openGalary(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,GALARY);
        //onActivityResult();
    }
    public boolean titlechanged()
    {
        if(!title.equals(e1.getText().toString()))
        {
            dbreference.child(title).child("title").setValue(e1.getText().toString());
            title=e1.getText().toString();
            dbreference.child("course").child("title").setValue(e1.getText().toString().trim());
            course helper = new course(title,des);
            dbreference.child("title").setValue(helper);

            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean deschanged()
    {
        if(!des.equals(e1.getText().toString()))
        {
            dbreference.child(title).child("des").setValue(e2.getText().toString());
            dbreference.child("course").child("des").setValue(e1.getText().toString().trim());
            des=e2.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }

}