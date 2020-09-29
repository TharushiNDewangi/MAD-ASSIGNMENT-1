package com.example.quectionandanswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdvertisementActivity2 extends AppCompatActivity {
    ArrayList<Advertisement> list;
    AdvertisementAdapter advertisementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement2);

        list = new ArrayList<>();
        final RecyclerView recyclerAdvertisement = findViewById(R.id.recyclerAdvertisement);
        advertisementAdapter = new AdvertisementAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerAdvertisement.setLayoutManager(layoutManager);
        recyclerAdvertisement.setAdapter(advertisementAdapter);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = db.getReference("Advertisement");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Advertisement advertisement = dataSnapshot1.getValue(Advertisement.class);
                    list.add(advertisement);
                }
                advertisementAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
