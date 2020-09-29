package com.example.quectionandanswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class AddAdvertisement extends AppCompatActivity {

    private Button btnChoose;
    private ImageView imageView;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage;
    StorageReference storageReference;
    EditText txt_title, txt_name, txt_mobile, txt_email, txt_description;
    Button btnAdd;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advertisement);
        context= this;
        btnChoose = (Button) findViewById(R.id.btnChoose);

        imageView = (ImageView) findViewById(R.id.imageView2);

        txt_title = findViewById(R.id.txt_title);
        txt_name = findViewById(R.id.txt_name);
        txt_mobile = findViewById(R.id.txt_mobile);
        txt_email = findViewById(R.id.txt_email);
        txt_description = findViewById(R.id.txt_description);
        btnAdd =findViewById(R.id.btnAdd);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath != null) {
                    final ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

                    final UploadTask uploadTask = ref.putFile(filePath);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        throw task.getException();
                                    }
                                    // Continue with the task to get the download URL
                                    return ref.getDownloadUrl();

                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        //thumb_download_url = task.getResult().toString();
                                        addAdvertisement(Objects.requireNonNull(task.getResult()).toString());
                                        progressDialog.dismiss();
                                        Toast.makeText(AddAdvertisement.this, "Uploaded", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddAdvertisement.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
                }

            }
        });




    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //private void uploadImage() {

       // if (filePath != null) {
         //   final ProgressDialog progressDialog = new ProgressDialog(this);
           // progressDialog.setTitle("Uploading...");
            //progressDialog.show();

            //final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

            //final UploadTask uploadTask = ref.putFile(filePath);
            //uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
              //  @Override
                //public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                  //  uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    //    @Override
                      //  public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        //    if (!task.isSuccessful()) {
                          //      throw task.getException();
                            //}
                            // Continue with the task to get the download URL
                            //return ref.getDownloadUrl();

                        //}
                    //}).addOnCompleteListener(new OnCompleteListener<Uri>() {
                      //  @Override
                        //public void onComplete(@NonNull Task<Uri> task) {
                          //  if (task.isSuccessful()) {
                                //thumb_download_url = task.getResult().toString();
                            //    addAdvertisement(Objects.requireNonNull(task.getResult()).toString());
                              //  progressDialog.dismiss();
                                //Toast.makeText(AddAdvertisement.this, "Uploaded", Toast.LENGTH_SHORT).show();

                            //}
                        //}
                    //});

                //}
            //}).addOnFailureListener(new OnFailureListener() {
              //  @Override
                //public void onFailure(@NonNull Exception e) {
                  //  progressDialog.dismiss();
                    //Toast.makeText(AddAdvertisement.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                //}
            //}).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
              //  @Override
                //public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                  //  double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                    //        .getTotalByteCount());
                    //progressDialog.setMessage("Uploaded " + (int) progress + "%");
                //}
            //});
        //}
    //}

    private void addAdvertisement(String imgUrl) {
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(txt_title.getText().toString());
        advertisement.setName(txt_name.getText().toString());
        advertisement.setMobile(txt_mobile.getText().toString());
        advertisement.setEmail(txt_email.getText().toString());
        advertisement.setDescription(txt_description.getText().toString());
        advertisement.setImageUrl(imgUrl);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = db.getReference("Advertisement");

        String mobile=txt_mobile.getText().toString();

        databaseReference.child(mobile).setValue(advertisement).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddAdvertisement.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void click(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(AddAdvertisement.this);
        builder.setTitle("Alert");
        builder.setMessage("Do You Want to Edit your questions?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AddAdvertisement.this, "Yes click", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AddAdvertisement.this, "No Click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AddAdvertisement.this, "cancel", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
