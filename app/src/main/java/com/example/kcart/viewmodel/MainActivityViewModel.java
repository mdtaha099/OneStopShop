package com.example.kcart.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.kcart.model.ProductRepo;
import com.example.kcart.model.Products;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private ProductRepo productRepo;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.productRepo = new ProductRepo(application);
    }

    public LiveData<List<Products>> getAllProducts(){
        return productRepo.getMutableLiveData();
    }
}
