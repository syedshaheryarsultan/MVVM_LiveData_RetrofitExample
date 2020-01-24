package com.zeroes.retrofitexample.retrofit;

import com.zeroes.retrofitexample.interfaces.ApiCalls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zeroes.retrofitexample.urls.ApiUrls.BASE_URL;

public class RetrofitInstance {

    private static Retrofit retrofit = null;

    public static ApiCalls getServices(){

        if(retrofit == null){

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit.create(ApiCalls.class);
    }

}
