package com.abdu.and_sep4.API;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static  TerrariumApi terrariumApi;


    public static TerrariumApi getTerrariumApi(){

        if (terrariumApi == null){
            terrariumApi = new Retrofit.Builder()
                    .baseUrl("https://terraeyes-db.azurewebsites.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TerrariumApi.class);
        }

        return terrariumApi;
    }












}
