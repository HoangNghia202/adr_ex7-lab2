package com.example.lab2v1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvCategory);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // Use GridLayoutManager with 2 columns

        // Create dummy product data
        productList = new ArrayList<>();
        productList.add(new Product("Pharmacy", "Description 1", R.drawable.pharmacy));
        productList.add(new Product("Registry", "Description 2", R.drawable.gift));
        productList.add(new Product("Cart view", "Description 3", R.drawable.cart));
        productList.add(new Product("Clothing", "Description 4", R.drawable.cloth));
        productList.add(new Product("Shoes", "Description 5", R.drawable.shoes));
        productList.add(new Product("Accessory", "Description 6", R.drawable.bag));
        productList.add(new Product("Baby", "Description 7", R.drawable.baby));
        productList.add(new Product("Home", "Description 8", R.drawable.home));
        productList.add(new Product("Patio garden", "Description 9", R.drawable.patio));


        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

        private List<Product> productList;

        public ProductAdapter(List<Product> productList) {
            this.productList = productList;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            Product product = productList.get(position);
            holder.textViewTitle.setText(product.getTitle());
            holder.imageViewProduct.setImageResource(product.getImageResourceId());
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView textViewTitle;
            ImageView imageViewProduct;

            public ProductViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.productName);
                imageViewProduct = itemView.findViewById(R.id.productImage);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Product product = productList.get(position);
                    Toast.makeText(v.getContext(), "Clicked on " + product.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private class Product {
        private String title;
        private String description;
        private int imageResourceId;

        public Product(String title, String description, int imageResourceId) {
            this.title = title;
            this.description = description;
            this.imageResourceId = imageResourceId;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getImageResourceId() {
            return imageResourceId;
        }
    }
}