package com.example.quectionandanswer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;


    public class  AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.ViewHolder> {
        private final ArrayList<Advertisement> mDataSet;
        private Context context;

        public  AdvertisementAdapter(Context context, ArrayList<Advertisement> list) {
            this.context = context;
            mDataSet = list;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            final TextView txt_mobile;
            final ImageView img_advertisement;

            ViewHolder(View view) {
                super(view);
                txt_mobile=view.findViewById(R.id.txt_mobile);
                img_advertisement=view.findViewById(R.id.img_advertisement);
            }
        }

        @NonNull
        @Override
        public AdvertisementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.advertisement_list, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull AdvertisementAdapter.ViewHolder holder, final int position) {
            final String title = mDataSet.get(position).getTitle();
            final String name = mDataSet.get(position).getName();
            final String mobile = mDataSet.get(position).getMobile();
            final String email = mDataSet.get(position).getEmail();
            final String description = mDataSet.get(position).getDescription();
            final String imageUrl = mDataSet.get(position).getImageUrl();
            holder.txt_mobile.setText(mobile);
            Glide.with(context)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.img_advertisement);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ViewAdvertisement.class);
                    intent.putExtra("title",title);
                    intent.putExtra("name",name);
                    intent.putExtra("mobile",mobile);
                    intent.putExtra("email",email);
                    intent.putExtra("description",description);
                    intent.putExtra("imageUrl",imageUrl);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }
}
