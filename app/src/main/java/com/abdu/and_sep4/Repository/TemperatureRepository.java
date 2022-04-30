package com.abdu.and_sep4.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.Temperatur;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemperatureRepository {

    private static TemperatureRepository instance;

    private final MutableLiveData<List<Temperatur>> temperatureRepositoryMutableLiveData;

    public TemperatureRepository() {
        temperatureRepositoryMutableLiveData = new MutableLiveData<>();



    }

    public static synchronized TemperatureRepository getInstance() {

        if (instance == null){
            instance = new TemperatureRepository();
        }

        return instance;
    }

    public MutableLiveData<List<Temperatur>> getTerrariumMutableLiveData() {
        return temperatureRepositoryMutableLiveData;
    }

    public LiveData<List<Temperatur>> getTemperatureByTerrariumId(int id){

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Temperatur>> call = terrariumApi.getTemperatureByTerrariumId(id);

        call.enqueue(new Callback<List<Temperatur>>() {
            @Override
            public void onResponse(Call<List<Temperatur>> call, Response<List<Temperatur>> response) {

                if (response.isSuccessful()){

                    temperatureRepositoryMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Temperatur>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Temperature by Terrarium id :(");

            }
        });


        return temperatureRepositoryMutableLiveData;

    }










}
