package com.example.elearningsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
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
    public Uri imageUrl;
    FirebaseDatabase rootnode;

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

        b4=findViewById(R.id.up3);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert(view);

            }
        });

        getdbvalues(titl);

    }

    public void returnback() {

        Intent intent = new Intent(this, viewcourse.class);
        startActivity(intent);

    }
    public void Alert(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        setTitle("Do you wat to update?");
        alert.setMessage(" You wanted to update ?");
        alert.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        update();

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

    public void update()
    {
        d = e2.getText().toString();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("course").child(titl);
        Map<String, Object> name = new HashMap();
        name.put("des", d);

        reference.updateChildren(name);

        Toast.makeText(UpdateCourses.this,d,Toast.LENGTH_LONG).show();

    }
    public void updateimg() {
        rootnode = FirebaseDatabase.getInstance();
        dbreference = rootnode.getReference("course");
        StorageReference refstorage = FirebaseStorage.getInstance().getReference("course");

        if (i1 != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");

            progressDialog.show();
            StorageReference storageReferance = FirebaseStorage.getInstance().getReference().child("images");
            final StorageReference fileRefrence = storageReferance.child(imageUrl.getLastPathSegment());
            fileRefrence.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRefrence.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        // progressDialog
                        @Override
                        public void onSuccess(Uri uri) {
                            String img = uri.toString();
                            Map<String, Object> imgdb = new HashMap();
                            imgdb.put("mimagurl", img);;

                            dbreference.child("course").child(titl);
                            dbreference.updateChildren(imgdb);
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "no file selected", Toast.LENGTH_SHORT).show();
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALARY && resultCode == RESULT_OK && data != null && data != null) {
            imageUrl = data.getData();
            i1.setImageURI(imageUrl);
            //picasso.with(this).load(imageUrl).into(i1);
        }
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