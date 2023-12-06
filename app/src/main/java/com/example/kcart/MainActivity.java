package com.example.kcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kcart.databinding.ActivityMainBinding;
import com.example.kcart.model.Products;
import com.example.kcart.view.ProductAdapter;
import com.example.kcart.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Products> productsArrayList;
    private RecyclerView recyclerView;
    private ActivityMainBinding activityMainBinding;
    private ProductAdapter productAdapter;
    private MainActivityViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        getProducts();
    }

    private void getProducts() {
        viewModel.getAllProducts().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> productsFromLiveData) {
                productsArrayList = (ArrayList<Products>) productsFromLiveData;
                displayProductsInRecyclerView();

            }
        });
    }

    private void displayProductsInRecyclerView() {
        recyclerView = activityMainBinding.recyclerView;

        productAdapter = new ProductAdapter(this, productsArrayList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productAdapter.notifyDataSetChanged();

    }
}