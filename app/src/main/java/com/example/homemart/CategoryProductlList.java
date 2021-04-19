package com.example.homemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoryProductlList extends AppCompatActivity {
    public String CategoryName;
    private Button potato;
    private Button tomato;
    private Button onion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_productl_list);
        CategoryName=getIntent().getExtras().get("Category").toString();
        Button p=(Button)findViewById(R.id.buttonpotato);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(CategoryProductlList.this,SellerDetail.class);
                startActivity(in);
            }
        });
    }
}