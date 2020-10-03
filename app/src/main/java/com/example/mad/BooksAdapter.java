package com.example.mad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private final ArrayList<Book> mDataSet;
    private Context context;

    public BooksAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        mDataSet = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBook;
        TextView txt_title, txt_author, txt_pages,txt_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.java_book_title);
            txt_author = itemView.findViewById(R.id.author_txt);
            txt_pages = itemView.findViewById(R.id.java_book_page_view);
            imgBook = itemView.findViewById(R.id.new_book_img);

        }
    }

    @NonNull
    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String title = mDataSet.get(position).getTitle();
        final String author = mDataSet.get(position).getAuthor();
        final String pages = mDataSet.get(position).getPages();
        final String image = mDataSet.get(position).getImage();
        final String price = mDataSet.get(position).getPrice();
        holder.txt_title.setText(title);
        holder.txt_author.setText(author);
        holder.txt_pages.setText(pages);
       // holder.txt_price.setText(price);
        Glide.with(context)
                .load(image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imgBook);
   }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}