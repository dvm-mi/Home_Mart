package com.example.homemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RetailerCategoryActivity extends AppCompatActivity {

    private Button LogoutRetailBtn;
    private ImageView Fruits, Vegetables, Breads;
    private ImageView Juices, Snacks, Meat;
    private ImageView PersonalCare;

    private  Button CheckOrdersBtn, maintainProductsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_category);

        Fruits = (ImageView) findViewById(R.id.ret_cat_fruits);
        Vegetables = (ImageView) findViewById(R.id.ret_cat_vegetables);
        Breads = (ImageView) findViewById(R.id.ret_cat_breads);
        Juices = (ImageView) findViewById(R.id.ret_cat_juice);
        Snacks = (ImageView) findViewById(R.id.ret_cat_snacks);
        Meat = (ImageView) findViewById(R.id.ret_cat_meat);
        PersonalCare = (ImageView) findViewById(R.id.ret_cat_perscare);

        LogoutRetailBtn = (Button) findViewById(R.id.logout_retailer_btn);
        LogoutRetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerCategoryActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity (intent);
                finish();
            }
        });

        CheckOrdersBtn= (Button) findViewById(R.id.check_orders_btn) ;
        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerCategoryActivity.this, RetailerNewOrdersActivity.class);
                startActivity (intent);
            }
        });

        maintainProductsBtn= (Button) findViewById(R.id.maintain_products_btn) ;
        maintainProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerCategoryActivity.this, DashboardActivity.class);
                intent.putExtra("Retailer","Retailer");
                startActivity (intent);
            }
        });



        Fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerCategoryActivity.this, RetAddNewProductActivity.class);
                intent.putExtra("category", "Fruits");
                startActivity(intent);
            }
        });

        Vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerCategoryActivity.this, RetAddNewProductActivity.class);
                intent.putExtra("category", "Vegetables");
                startActivity(intent);
            }
        });

        Breads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerCategoryActivity.this, RetAddNewProductActivity.class);
                intent.putExtra("category", "Breads");
                startActivity(intent);
            }
        });

        Juices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerCategoryActivity.this, RetAddNewProductActivity.class);
                intent.putExtra("category", "Juices");
                startActivity(intent);
            }
        });

        Snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerCategoryActivity.this, RetAddNewProductActivity.class);
                intent.putExtra("category", "Snacks");
                startActivity(intent);
            }
        });

        Meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerCategoryActivity.this, RetAddNewProductActivity.class);
                intent.putExtra("category", "Meat");
                startActivity(intent);
            }
        });

        PersonalCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetailerCategoryActivity.this, RetAddNewProductActivity.class);
                intent.putExtra("category", "Personal Care");
                startActivity(intent);
            }
        });

    }
}