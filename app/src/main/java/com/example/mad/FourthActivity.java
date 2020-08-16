package com.example.mad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {

    Button back;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        back = findViewById(R.id.btn8);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backTo = new Intent(FourthActivity.this,FifthActivity.class);
                startActivity(backTo);
            }
        });
    }

    public void Click (View view) {
        AlertDialog.Builder builder= new AlertDialog.Builder(FourthActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Do You Want to Delete Profile?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(FourthActivity.this, "Yes Click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(FourthActivity.this, "No Click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(FourthActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}