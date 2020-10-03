package com.example.mad;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EighthActivity extends AppCompatActivity {
    EditText Username, U_course, U_mobile, U_password, fullName;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth);

        //Hooks
        fullName = findViewById(R.id.user_fullname);
        Username = findViewById(R.id.user_user_name);
        U_course = findViewById(R.id.user_user_course);
        U_mobile = findViewById(R.id.user_Mobile);
        U_password = findViewById(R.id.user_pass_word);
        add = findViewById(R.id.button4);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get and set values
                ProfileHelper profileHelper = new ProfileHelper();
                profileHelper.setName(fullName.getText().toString());
                profileHelper.setUsername(Username.getText().toString());
                profileHelper.setPassword( U_password.getText().toString());
                profileHelper.setCourse( U_course.getText().toString());
                profileHelper.setPhone(U_mobile.getText().toString());

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference= db.getReference("profile");

                String username = Username.getText().toString();

                //validate Username is not be empty
                if (TextUtils.isEmpty(Username.getText().toString())){
                    Toast.makeText(EighthActivity.this, "Empty Details Not Allowed", Toast.LENGTH_SHORT).show();
                }else if (!U_mobile.getText().toString().matches("[0-9]{10}")){
                    U_mobile.setError("Enter Only 10 Digit Mobile Number");
                }else
                    databaseReference.child(username).setValue(profileHelper).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(EighthActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                /*databaseReference.child(username).setValue(profileHelper).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EighthActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });*/



                //String name =  fullName.getText().toString();
                //String username = Username.getText().toString();
                //String course = U_course.getText().toString();
               // String phone =  U_mobile.getText().toString();
               // String password = U_password.getText().toString();

               // ProfileHelper profileHelper = new ProfileHelper(name,username,course,phone,password);
               //reference.setValue(profileHelper);
            //}


    });

    }

}