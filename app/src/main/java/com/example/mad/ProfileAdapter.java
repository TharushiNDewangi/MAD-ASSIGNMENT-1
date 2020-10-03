package com.example.mad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter <ProfileAdapter.ViewHolder> {
    private final ArrayList<ProfileHelper> mDataSet;
    private Context context;

    public ProfileAdapter(Context context, ArrayList<ProfileHelper> list) {
        this.context = context;
        mDataSet = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView txt_name;
        final TextView txt_course;


        ViewHolder(View view) {
            super(view);
            txt_name = view.findViewById(R.id.textView5);
            txt_course = view.findViewById(R.id.textView9);
        }
    }

    @NotNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){

        View v = LayoutInflater.from(context).inflate(R.layout.user_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NotNull ProfileAdapter.ViewHolder holder ,final int position){

        final String name = mDataSet.get(position).getName();
        final String username = mDataSet.get(position).getUsername();
        final String course = mDataSet.get(position).getCourse();
        final String phone = mDataSet.get(position).getPhone();
        final String password = mDataSet.get(position).getPassword();
        holder.txt_name.setText(name);
        holder.txt_course.setText(course);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ViewProfile.class);
                intent.putExtra("name",name);
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                intent.putExtra("course",course);
                intent.putExtra("phone",phone);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


}
