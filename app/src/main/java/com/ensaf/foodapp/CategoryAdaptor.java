package com.ensaf.foodapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder>{
    ArrayList<Category>categories;

    public CategoryAdaptor(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdaptor.ViewHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).getTitle());
        String picUrl="";
        switch (position){
            case 0:{
                picUrl="pizza_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background1));
                break;
            }
            case 1:{
                picUrl="burger_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background2));
                break;
            }
            case 2:{
                picUrl="hotdog_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background3));
                break;
            }
            case 3:{
                picUrl="soda_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background4));
                break;
            }
            case 4:{
                picUrl="donut_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background5));
                break;
            }

        }
        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName=itemView.findViewById(R.id.title);
            categoryPic=itemView.findViewById(R.id.pic);
            mainLayout=itemView.findViewById(R.id.mainLayout);


        }
    }
}
