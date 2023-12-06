package com.example.kcart.Api;

import com.example.kcart.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsApiService {

    @GET("products")
    Call<Result> getResult();
}
