package com.example.retrofitcrud_client0;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getBook(String url){
        if(retrofit==null){
            retrofit =  new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        } else return retrofit;
    }


}