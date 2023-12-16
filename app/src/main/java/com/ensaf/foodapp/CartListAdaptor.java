package com.ensaf.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ensaf.foodapp.Helper.Cart;

import java.util.ArrayList;

public class CartListAdaptor extends RecyclerView.Adapter<CartListAdaptor.ViewHolder> {

    private ArrayList<Food> food;
    private Cart cart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdaptor(ArrayList<Food> food, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.food = food;
        this.cart = new Cart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate=LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdaptor.ViewHolder holder, int position) {
        holder.title.setText(food.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(food.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((food.get(position).getNumberInCart() * food.get(position).getFee()) *100)/100));
        holder.num.setText(String.valueOf(food.get(position).getNumberInCart()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(food.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.plusNumberFood(food, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.MinusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.minusNumberFood(food, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return food.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView title,feeEachItem;
        ImageView pic,plusItem,MinusItem;
        TextView totalEachItem, num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCart);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            num=itemView.findViewById(R.id.numberItemTxt);
            plusItem=itemView.findViewById(R.id.PlusCartBtn);
            MinusItem= itemView.findViewById(R.id.MinusCartBtn);
        }
    }
}
