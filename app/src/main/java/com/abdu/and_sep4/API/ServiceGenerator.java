package com.abdu.and_sep4.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static  TerrariumApi terrariumApi;


    public static TerrariumApi getTerrariumApi(){
        OkHttpClient.Builder ok = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        ok.addInterceptor(httpLoggingInterceptor);
        if (terrariumApi == null){
            terrariumApi = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(ok.build())
                    .build()
                    .create(TerrariumApi.class);
        }

        return terrariumApi;
    }












}
