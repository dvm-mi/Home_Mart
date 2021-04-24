package com.example.homemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class RetailerMaintainProductsActivity extends AppCompatActivity {

    private Button applyChangesBtn, deleteBtn;
    private EditText name, price, description;
    private ImageView imageView;
    private String ProductID = "";
    private DatabaseReference productsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_maintain_products);

        ProductID = getIntent().getStringExtra("pid");

        productsRef = FirebaseDatabase.getInstance().getReference().child("Products").child(ProductID);

        applyChangesBtn =  (Button) findViewById(R.id.apply_changes_maintain);
        name= (EditText) findViewById(R.id.product_name_maintain);
        price= (EditText) findViewById(R.id.product_price_maintain);
        description= (EditText) findViewById(R.id.product_description_maintain);
        imageView= (ImageView) findViewById(R.id.product_image_maintain);
        deleteBtn =  (Button) findViewById(R.id.delete_product_btn);

        displaySpecificProductInfo();

        applyChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteThisProduct();
            }
        });

    }

    private void deleteThisProduct() {

        productsRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(RetailerMaintainProductsActivity.this, RetailerCategoryActivity.class);
                startActivity(intent);

                Toast.makeText(RetailerMaintainProductsActivity.this, "The product has been removed successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void applyChanges() {

        String pName= name.getText().toString();
        String pPrice= price.getText().toString();
        String pDescription= description.getText().toString();

        if (pName.equals("")){
            Toast.makeText(this, "Fill in Product Name", Toast.LENGTH_SHORT).show();
        }
        else if (pPrice.equals("")){
            Toast.makeText(this, "Fill in Product Price", Toast.LENGTH_SHORT).show();
        }
        else if (pDescription.equals("")){
            Toast.makeText(this, "Fill in Product Description", Toast.LENGTH_SHORT).show();
        }
        else {
            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", ProductID);
            productMap.put("description", pDescription);
            productMap.put("price", pPrice);
            productMap.put("pname", pName);

            productsRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RetailerMaintainProductsActivity.this, "Changes applied successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RetailerMaintainProductsActivity.this, RetailerCategoryActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    private void displaySpecificProductInfo() {
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String pName = snapshot.child("pname").getValue().toString();
                    String pPrice = snapshot.child("price").getValue().toString();
                    String pDescription = snapshot.child("description").getValue().toString();
                    String pImage = snapshot.child("image").getValue().toString();

                    name.setText(pName);
                    price.setText(pPrice);
                    description.setText(pDescription);
                    Picasso.get().load(pImage).into(imageView);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}