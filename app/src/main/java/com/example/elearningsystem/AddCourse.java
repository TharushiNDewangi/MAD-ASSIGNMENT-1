package com.example.elearningsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddCourse extends AppCompatActivity {

    static int GALARY = 1;
    EditText e1,e2;
    Spinner s;
    Button b1;
    ImageView i1;
    public Uri imageUrl;
    FirebaseDatabase rootnode;
    DatabaseReference ref;

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
        StorageReference refstorage = FirebaseStorage.getInstance().getReference("course");

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
                            String title = e1.getText().toString();
                            String des = e2.getText().toString();
                            String course = s.getSelectedItem().toString();

                            course helper = new course(title,des,course,img);
                            ref.child(title).setValue(helper);
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

    private void alert(String message)
    {
        AlertDialog dlg = new AlertDialog.Builder(AddCourse.this).setTitle("Message").setMessage(message)
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