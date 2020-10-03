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

    Button cart,goBookstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        cart = findViewById(R.id.button8);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goCart = new Intent(FourthActivity.this,NinthActivity.class);
                startActivity(goCart);
            }
        });

        goBookstore = findViewById(R.id.button7);

        goBookstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addBook = new Intent(FourthActivity.this, AddBook.class);
                startActivity(addBook);
            }
        });

    }

    /*public void Click (View view) {
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
    }*/
}