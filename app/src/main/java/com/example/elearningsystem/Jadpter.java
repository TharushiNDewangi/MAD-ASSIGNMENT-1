package com.example.elearningsystem;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Jadpter extends RecyclerView.ViewHolder {
    public TextView t1;
    CardView c1;

    public Jadpter(@NonNull View itemView) {
        super(itemView);
        t1=(TextView) itemView.findViewById(R.id.title);

        c1 = (CardView) itemView.findViewById(R.id.jcardview);

    }
}
