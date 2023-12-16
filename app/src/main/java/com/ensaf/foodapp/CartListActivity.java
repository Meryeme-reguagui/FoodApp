package com.ensaf.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ensaf.foodapp.Helper.Cart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private Cart cart;
    TextView totalFeeTxt,taxTxt,totalTxt, deliveryTxt ,emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        cart=new Cart(this);
        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,MainActivity.class));
            }
        });

    }
    private void initView() {
        recyclerViewList=findViewById(R.id.recyclerView1);
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.delivreyTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyText);
        scrollView=findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);

    }
    private  void  initList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdaptor(cart.getListCart(),this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(cart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private  void CalculateCart(){
        double percentTax=0.02;
        double delivery=10;
        tax=Math.round((cart.getTotalFee()*percentTax)*100)/100;
        double total=Math.round((cart.getTotalFee() + tax+delivery)*100)/100;
        double itemTotal=Math.round(cart.getTotalFee()*100)/100;

        totalFeeTxt.setText(itemTotal+"DH");
        taxTxt.setText(tax+"DH");
        deliveryTxt.setText(delivery+"DH");
        totalTxt.setText(total+"DH");


    }
}