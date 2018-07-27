package com.blogbasbas.fortraining.network;

import com.blogbasbas.fortraining.helpers.MyConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class InitRetrofit {

    public static Retrofit setInit(){
        return new Retrofit.Builder()
                .baseUrl(MyConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getInstance(){
        return setInit().create(ApiServices.class);
    }
}
