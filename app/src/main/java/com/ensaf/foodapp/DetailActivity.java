package com.ensaf.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ensaf.foodapp.Helper.Cart;

public class DetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt,feeTxt,descriptionTxt,numberOrderTxt;
    private ImageView plusBtn,MinusBtn, picFood;
    private Food object;
    int numberOrder=1;
    private Cart cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Food receivedFood = (Food) getIntent().getSerializableExtra("object");

        if (receivedFood != null) {
            Log.d("DetailActivity", "Received Food Title: " + receivedFood.getTitle());
            Log.d("DetailActivity", "Received Food Pic: " + receivedFood.getPic());
                cart=new Cart(this);
                initView();
                getBundle();
        } else {
            Log.e("DetailActivity", "No Food object received!");
        }
    }


    private void getBundle() {

        object=(Food) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText(object.getFee()+ "DH");
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder=numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder>1){
                    numberOrder=numberOrder-1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                cart.insertFood(object);
            }
        });
    }

    private void initView() {
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.priceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberOrderTxt);
        plusBtn=findViewById(R.id.PlusBtn);
        MinusBtn=findViewById(R.id.MinusBtn);
        picFood = findViewById(R.id.pic);

    }
}