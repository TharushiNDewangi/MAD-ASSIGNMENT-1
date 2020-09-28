package com.example.quectionandanswer;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    private final ArrayList<QuestionsInfo> mDataSet;
    private Context context;

    public QuestionsAdapter(Context context, ArrayList<QuestionsInfo> list) {
        this.context = context;
        mDataSet = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView txt_name;
        final TextView txt_question;

        ViewHolder(View view) {
            super(view);
            txt_name = view.findViewById(R.id.txt_name);
            txt_question = view.findViewById(R.id.txt_question);
        }
    }

    @NonNull
    @Override
    public QuestionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.questions_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String name = mDataSet.get(position).getName();
        final String question = mDataSet.get(position).getQuestion();

        holder.txt_name.setText(name);
        holder.txt_question.setText(question);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ViewQandASession.class);
                intent.putExtra("name",name);
                intent.putExtra("question",question);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}