package com.abdu.and_sep4.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.FoodDispenser;
import com.abdu.and_sep4.Shared.Terrarium;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRepository {

    private static FoodRepository instance;
    private final MutableLiveData<FoodDispenser> foodDispenserMutableLiveData;


    public FoodRepository() {
        foodDispenserMutableLiveData = new MutableLiveData<>();
    }


    public static synchronized FoodRepository getInstance() {

        if (instance == null){
            instance = new FoodRepository();
        }

        return instance;
    }

    public LiveData<FoodDispenser> addFood(FoodDispenser foodDispenser){
        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<FoodDispenser> call = terrariumApi.addFood(foodDispenser);

        call.enqueue(new Callback<FoodDispenser>() {
            @Override
            public void onResponse(Call<FoodDispenser> call, Response<FoodDispenser> response) {
                if(response.isSuccessful()) {
                    foodDispenserMutableLiveData.setValue(response.body());
                    Log.e("Retrofit", "working adding terrarium :(");

                }
            }

            @Override
            public void onFailure(Call<FoodDispenser> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong adding Food :(");
            }
        });



        return foodDispenserMutableLiveData;

    }
}
