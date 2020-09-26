package com.example.elearningsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Course extends AppCompatActivity {

    static int GALARY = 1;
    EditText e1,e2;
    Spinner s;
    Button b1;
    ImageView i1;
    public Uri imageUrl;
    FirebaseDatabase rootnode;
    DatabaseReference ref;
    private StorageReference refstorage;
   // private DatabaseReference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        b1=findViewById(R.id.add);
        e1=findViewById(R.id.CourseTitle);
        e2 =findViewById(R.id.coursedescription);
        s = (Spinner) findViewById(R.id.spinner);
        i1=findViewById(R.id.imageView);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalary();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcourse();
            }
        });
    }

    public void addcourse() {
        rootnode = FirebaseDatabase.getInstance();
        ref = rootnode.getReference("course");
        refstorage = FirebaseStorage.getInstance().getReference("course");

//
//        //get all the values
//        String title = e1.getText().toString();
//        String des = e2.getText().toString();
//        String course = s.getSelectedItem().toString();

        //final String img ;
        if (i1 != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
//            StorageReference fileref = refstorage.child( System.currentTimeMillis() + "." + getFileExtension(imageUrl));
//            fileref.putFile(imageUrl)
            StorageReference fileref = refstorage.child( "images/*"+ UUID.randomUUID().toString());
            fileref.putFile(imageUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           // Handler handler=new Handler()
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "upload susseed", Toast.LENGTH_SHORT).show();


                            //get all the values
                            String title = e1.getText().toString();
                            String des = e2.getText().toString();
                            String course = s.getSelectedItem().toString();

                            AddCourseHelper helper = new  AddCourseHelper(title,des,course,taskSnapshot.toString());
                            ref.child(title).setValue(helper);

                            Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
//                            img = taskSnapshot.toString();
                            //helper
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                            .getTotalByteCount());
                    progressDialog.setMessage("Uploaded "+(int)progress+"%");
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "no file selected", Toast.LENGTH_SHORT).show();
        }
        //String img = i1.getDownloaed.toString();
//        AddCourseHelper helper = new  AddCourseHelper(title,des,course);
//        ref.child(title).setValue(helper);
//
//        Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();

        //call second activity
        //Intent intent = new Intent(this,viewcourse.class);
        //

        //startActivity(intent);

    }

    private String getFileExtension(Uri uri) {
        ContentResolver CR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(CR.getType(uri));

    }

    private void alert(String message)
    {
        AlertDialog dlg = new AlertDialog.Builder(Course.this).setTitle("Message").setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }
    public void openGalary(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,GALARY);
        //onActivityResult();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALARY && resultCode==RESULT_OK && data!= null && data!=null)
        {
            imageUrl = data.getData();
            i1.setImageURI(imageUrl);
            //picasso.with(this).load(imageUrl).into(i1);
        }


    }
}