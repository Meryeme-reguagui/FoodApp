package com.ensaf.foodapp;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder>{
    ArrayList<Food>popularFood;


    public PopularAdaptor(ArrayList<Food> popularFood) {
        this.popularFood = popularFood;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdaptor.ViewHolder holder, int position) {
        Food foodItem = popularFood.get(position);

        holder.title.setText(foodItem.getTitle());
        holder.fee.setText(String.valueOf(foodItem.getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(
                foodItem.getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food foodItem = popularFood.get(position);

                Log.d("PopularAdapter", "Title: " + foodItem.getTitle());
                Log.d("PopularAdapter", "Pic: " + foodItem.getPic());

                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("object", foodItem);
                holder.itemView.getContext().startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee;
        ImageView pic;
        TextView addBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            fee=itemView.findViewById(R.id.fee);
            pic=itemView.findViewById(R.id.pic);
            addBtn=itemView.findViewById(R.id.addBtn);


        }
    }
}
