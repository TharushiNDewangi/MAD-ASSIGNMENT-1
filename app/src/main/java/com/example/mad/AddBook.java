package com.example.mad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
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

import static java.util.Objects.requireNonNull;

public class AddBook extends AppCompatActivity {
    private Button choose;
    private ImageView imgView;
    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage;
    StorageReference storageReference;
    EditText txt_bookName,txt_authorName,No_Views;
    Button btnAdd;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        context = this;
        choose = (Button) findViewById(R.id.buttonChoose);
        imgView = (ImageView) findViewById(R.id.imageView3);

        txt_bookName = findViewById(R.id.txt_Book);
        txt_authorName = findViewById(R.id.txt_Author);
        No_Views = findViewById(R.id.No_of_Views);
        btnAdd = findViewById(R.id.Btn_add);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        //image upload
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filepath != null){
                    final ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    final StorageReference ref = storageReference.child("image/" + UUID.randomUUID().toString());

                    final UploadTask uploadTask = ref.putFile(filepath);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()){
                                        throw task.getException();
                                    }
                                    //continue with the task to get the download URL
                                    return ref.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()){
                                        addBook(requireNonNull(task.getResult()).toString());
                                        progressDialog.dismiss();
                                        Toast.makeText(AddBook.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddBook.this,"Failed" + e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot
                            .getTotalByteCount());

                            progressDialog.setMessage("Uploaded" + (int) progress + "%");
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
            startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_REQUEST);

    }

    private void addBook(String imgUrl){

        Book book = new Book();
        book.setTitle(txt_bookName.getText().toString());
        book.setAuthor(txt_authorName.getText().toString());
        book.setPages(No_Views.getText().toString());
        book.setImage(imgUrl);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1  = db.getReference("bookstore");

        String title = txt_bookName.getText().toString();

        //validate bookname can not be empty
        if (TextUtils.isEmpty(txt_bookName.getText().toString())){
            Toast.makeText(AddBook.this, "Empty Details Not Allowed", Toast.LENGTH_SHORT).show();
        }else {

            databaseReference1.child(title).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(AddBook.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });

        }

       /* databaseReference1.child(title).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddBook.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filepath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imgView.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }





}