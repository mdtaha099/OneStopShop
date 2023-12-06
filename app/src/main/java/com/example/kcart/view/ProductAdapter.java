package com.example.kcart.view;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kcart.DetailsActivity;
import com.example.kcart.R;
import com.example.kcart.databinding.ProductCardBinding;
import com.example.kcart.model.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private ArrayList<Products> productsArrayList;

    public ProductAdapter(Context context, ArrayList<Products> productsArrayList) {
        this.context = context;
        this.productsArrayList = productsArrayList;
    }





    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductCardBinding binding = DataBindingUtil
                .inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.product_card,
                    parent,
                    false
        );

        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Products products = productsArrayList.get(position);
        holder.productCardBinding.setProduct(products);
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size() ;
    }







    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private ProductCardBinding productCardBinding;


        public ProductViewHolder(ProductCardBinding productCardBinding) {
            super(productCardBinding.getRoot() );
            this.productCardBinding = productCardBinding;

            productCardBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = productCardBinding.getProduct().getTitle();
                    String desc = productCardBinding.getProduct().getDescription();
                    int price = productCardBinding.getProduct().getPrice();
                    Double discPercent = productCardBinding.getProduct().getDiscountPercentage();
                    String rating = productCardBinding.getProduct().getRating().toString();
                    String stock = productCardBinding.getProduct().getStock().toString();
                    String brand = productCardBinding.getProduct().getBrand();
                    String category = productCardBinding.getProduct().getCategory();

                    List<String> img = productCardBinding.getProduct().getImages();

                    Intent i = new Intent(context.getApplicationContext(), DetailsActivity.class);
                    i.putExtra("title", title);
                    i.putExtra("desc", desc);
                    i.putExtra("price", price);
                    i.putExtra("discPercent", discPercent);
                    i.putExtra("rating", rating);
                    i.putExtra("stock", stock);
                    i.putExtra("brand", brand);
                    i.putExtra("category", category);

                    i.putExtra("images", img.toArray(new String[0]));

//                    Toast.makeText(context.getApplicationContext(), ""+title, Toast.LENGTH_SHORT).show();
                    context.startActivity(i);
                }
            });

        }
    }
}
