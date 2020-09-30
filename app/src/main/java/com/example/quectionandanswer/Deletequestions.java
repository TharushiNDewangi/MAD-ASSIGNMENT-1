package com.example.quectionandanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Deletequestions extends AppCompatActivity {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletequestions);
        back=findViewById(R.id.button8);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backTo = new Intent(Deletequestions.this,ViewQandASession.class);
                startActivity(backTo);
            }
        });
    }

    public void click(View view) {
        AlertDialog.Builder builder= new AlertDialog.Builder(Deletequestions.this);
        builder.setTitle("Alert");
        builder.setMessage("Do You Want to Delete Your Questions?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Deletequestions.this, "Yes Click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Deletequestions.this, "No Click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Deletequestions.this, "No Click", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}