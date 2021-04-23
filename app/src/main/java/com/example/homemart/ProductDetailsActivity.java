package com.example.homemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.homemart.model.Products;
import com.example.homemart.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import static java.lang.System.load;

public class ProductDetailsActivity extends AppCompatActivity {
    private Button AddToCartButton;
    private ImageView ProductImage;
    private ElegantNumberButton NumberBtn;
    private TextView ProductPrice, ProductDescription, ProductName;
    private String ProductID = "", state = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ProductID = getIntent().getStringExtra("pid");

        AddToCartButton= (Button) findViewById(R.id.add_product_to_cart);
         NumberBtn= (ElegantNumberButton) findViewById(R.id.number_btn);
         ProductImage=  (ImageView) findViewById(R.id.product_image_details);
         ProductName= (TextView) findViewById(R.id.product_name_details);
        ProductDescription= (TextView) findViewById(R.id.product_description_details);
        ProductPrice= (TextView) findViewById(R.id.product_price_details);

        getProductDetails(ProductID);

        AddToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                addingToCartList();

                if (state.equals("Order Placed") || state.equals("Order Shipped")){
                    Toast.makeText(ProductDetailsActivity.this, "You can add and purchase more products, once your order is shipped or confirmed", Toast.LENGTH_LONG).show();
                }
                else {
                    addingToCartList();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        CheckOrderState();
    }

    private void addingToCartList() {
        String SaveCurrentTime, SaveCurrentDate;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("MMM dd, yyyy");
        SaveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
        SaveCurrentTime = currentTime.format(calForDate.getTime());


        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("pid" , ProductID );
        cartMap.put("pname" , ProductName.getText().toString());
        cartMap.put("price" , ProductPrice.getText().toString());
        cartMap.put("date" , SaveCurrentDate);
        cartMap.put("time" ,SaveCurrentTime );
        cartMap.put("quantity" , NumberBtn.getNumber() );
        cartMap.put("discount" , "" );

        cartListRef.child("User View").child(Prevalent.currentOnlineUsers.getPhone())
                .child("Products").child(ProductID).updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            cartListRef.child("Admin View").child(Prevalent.currentOnlineUsers.getPhone())
                                    .child("Products").child(ProductID).updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Toast.makeText(ProductDetailsActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ProductDetailsActivity.this, DashboardActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    private void getProductDetails(String productID) {

        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Products");

        productRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Products products = snapshot.getValue(Products.class);

                    ProductName.setText(products.getPname());
                    ProductPrice.setText(products.getPrice());
                    ProductDescription.setText(products.getDescription());
                    Picasso.get().load(products.getImage()).into(ProductImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void CheckOrderState(){
        DatabaseReference ordersRef;
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUsers.getPhone());

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String shippingState = snapshot.child("state").getValue().toString();


                    if (shippingState.equals("Shipped")){

                        state = "Order Shipped";

                    }
                    else if (shippingState.equals("Not Shipped")){

                        state = "Order Placed";
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}