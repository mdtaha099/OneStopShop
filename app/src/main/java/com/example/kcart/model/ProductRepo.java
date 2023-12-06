package com.example.kcart.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.kcart.Api.ProductsApiService;
import com.example.kcart.Api.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepo {

    private ArrayList<Products> productsArrayList = new ArrayList<>();
    private MutableLiveData<List<Products>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public ProductRepo(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Products>> getMutableLiveData(){
        ProductsApiService productsApiService = RetrofitInstance.getService();
        Call<Result> call = productsApiService.getResult();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                if(result != null && result.getProducts() != null){
                    productsArrayList = (ArrayList<Products>) result.getProducts();
                    mutableLiveData.setValue(productsArrayList);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
