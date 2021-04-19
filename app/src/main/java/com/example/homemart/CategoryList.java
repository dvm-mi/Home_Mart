package com.example.homemart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homemart.CategoryProductlList;

import java.nio.InvalidMarkException;

public class CategoryList extends AppCompatActivity {
    private ImageView beverages;
    private ImageView fruit,pc;
    private ImageView meat,bakery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ImageView vegetable = (ImageView) findViewById(R.id.vegetable);
        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryList.this, CategoryProductlList.class);
                intent.putExtra("Category","vegetable");
                startActivity(intent);

            }
        });
    }
}