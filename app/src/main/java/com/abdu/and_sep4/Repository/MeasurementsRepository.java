package com.abdu.and_sep4.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.Measurements;
import com.abdu.and_sep4.Shared.Temperatur;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeasurementsRepository {

    private static MeasurementsRepository instance;

    private final MutableLiveData<List<Measurements>> temperatureRepositoryMutableLiveData;


    public MeasurementsRepository() {
        temperatureRepositoryMutableLiveData = new MutableLiveData<>();

    }

    public static synchronized MeasurementsRepository getInstance() {

        if (instance == null){
            instance = new MeasurementsRepository();
        }

        return instance;
    }

    public MutableLiveData<List<Measurements>> getTerrariumMutableLiveData() {
        return temperatureRepositoryMutableLiveData;
    }

    public LiveData<List<Measurements>> getMeasurementsByTerrariumId(int id){

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Measurements>> call = terrariumApi.getMeasurementsByTerrariumId(id);

        call.enqueue(new Callback<List<Measurements>>() {
            @Override
            public void onResponse(Call<List<Measurements>> call, Response<List<Measurements>> response) {

                if (response.isSuccessful()){

                    temperatureRepositoryMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Measurements>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Temperature by Terrarium id :(");

            }
        });


        return temperatureRepositoryMutableLiveData;

    }










}
