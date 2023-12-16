package com.ensaf.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter1,adapter2;
    private  RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<Category> categoryList=new ArrayList<>();
        categoryList.add(new Category("Pizza" , "pizza_icon"));
        categoryList.add(new Category("Burger" , "burger_icon"));
        categoryList.add(new Category("Hotdog" , "hotdog_icon"));
        categoryList.add(new Category("Drink" , "soda_icon"));
        categoryList.add(new Category("Donut" , "donut_icon"));

        adapter1=new CategoryAdaptor(categoryList);
        recyclerViewCategoryList.setAdapter(adapter1);

    }
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<Food> foodList=new ArrayList<>();
        foodList.add(new Food("Pepperoni pizza","pep_pizza","- slices pepperoni \n- mozzerella cheese \n-fress oregano \n-ground black pepper \n-pizza sauce",60.00 ));
        foodList.add(new Food("Cheese Burger","ch_burger","-beef \n-Gouda Cheese \n-Special Sauce \n-Lettuce \n-tomato",64.00 ));
        foodList.add(new Food("Vegetable pizza","vg_pizza","-olive oil \n-Vegitable oil \n-pitted kalamata \n-cherry tomatoes \n-fresh oregano \n-basil",50.00));

        adapter2=new PopularAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapter2);


    }
}