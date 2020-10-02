package com.example.elearningsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class UpdateCourses extends AppCompatActivity {

    Button b1,b4;
    EditText e2;
    ImageView i1;
    TextView t1;
    static int GALARY = 1;
    String titl;
    DatabaseReference dbreference;
    String title,des,img;
    String d;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_courses);
        dbreference=FirebaseDatabase.getInstance().getReference().child("course");



        t1 = findViewById(R.id.viewtitle);
        e2 = findViewById(R.id.description);
        b1 = findViewById(R.id.cancel);
        i1 = findViewById(R.id.imageView);

        //title=getIntent().getStringExtra("title");


//
        titl = getIntent().getExtras().getString("title");

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


       // titl=getIntent().getStringExtra("title");

        b4=findViewById(R.id.up3);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert(view);
                //update();
            }
        });
        //viewvalues();

        getdbvalues(titl);

        String d1 = e2.getText().toString();
        //update(d1);

    }

    public void returnback() {

        Intent intent = new Intent(this, viewcourse.class);
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
                        uo();
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


    public void getdbvalues(final String val)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("course").child(val);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 title = snapshot.child("title").getValue().toString();
                 des  = snapshot.child("des").getValue().toString();
                img  = snapshot.child("mimagurl").getValue().toString();
                Picasso.get().load(img).into(i1);
                t1.setText(title);
                e2.setText(des);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openGalary(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,GALARY);
        //onActivityResult();
    }
    public void update( String descr)
    {

        dbreference = FirebaseDatabase.getInstance().getReference("course").child(title).child("des");
        dbreference.setValue(descr);
    }
    public void uo()
    {
        d = e2.getText().toString();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("course").child(titl);
        Map<String, Object> name = new HashMap();
        name.put("des", d);
        reference.updateChildren(name);

        Toast.makeText(UpdateCourses.this,d,Toast.LENGTH_LONG).show();

    }

//    private void editData(String strTitle, final String strBody, String image){
//        Query editQuery = dbreference.orderByChild("title").equalTo(strTitle);
//        editQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot edtData: dataSnapshot.getChildren()){
//                    edtData.getRef().child("content").setValue(strBody);
//                }
//                Toast.makeText(UpdateCourses.this,"Data Edited",Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(UpdateCourses.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    public boolean titlechanged()
//    {
//        if(!title.equals(e1.getText().toString()))
//        {
//            dbreference.child(title).child("title").setValue(e1.getText().toString());
//            title=e1.getText().toString();
//            dbreference.child("course").child("title").setValue(e1.getText().toString().trim());
//            course helper = new course(title,des);
//            dbreference.child(title).setValue(helper);
//
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//    public boolean deschanged()
//    {
//        if(!des.equals(e1.getText().toString()))
//        {
//            dbreference.child(title).child("des").setValue(e2.getText().toString());
//            dbreference.child("course").child("des").setValue(e1.getText().toString().trim());
//            des=e2.getText().toString();
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//    public void showupdate(String titl,String description)
//    {
//        AlertDialog.Builder dialogb=new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        //final View dialogview = inflater.inflate(R.layout.)
//    }


}