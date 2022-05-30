package com.abdu.and_sep4.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeasurementsRepository {

    private static MeasurementsRepository instance;

    private final MutableLiveData<List<TemperatureMeasurement>> temperatureMeasurementMutableLiveData;
    private final MutableLiveData<List<HumidityMeasurement>> humidityMeasurementMutableLiveData;
    private final MutableLiveData<List<Co2Measurement>> co2MeasurementMutableLiveData;


    public MeasurementsRepository() {
        temperatureMeasurementMutableLiveData = new MutableLiveData<>();
        humidityMeasurementMutableLiveData = new MutableLiveData<>();
        co2MeasurementMutableLiveData = new MutableLiveData<>();

    }

    public static synchronized MeasurementsRepository getInstance() {

        if (instance == null){
            instance = new MeasurementsRepository();
        }

        return instance;
    }


    public MutableLiveData<List<TemperatureMeasurement>> getTemperatureMeasurementMutableLiveData() {
        return temperatureMeasurementMutableLiveData;
    }

    public MutableLiveData<List<HumidityMeasurement>> getHumidityMeasurementMutableLiveData() {
        return humidityMeasurementMutableLiveData;
    }

    public LiveData<List<TemperatureMeasurement>> getTemperatureMeasurementsByUserIdAndEui(String userid, String eui){

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<TemperatureMeasurement>> call = terrariumApi.getTemperatureMeasurementByUserId(userid, eui);

        call.enqueue(new Callback<List<TemperatureMeasurement>>() {
            @Override
            public void onResponse(Call<List<TemperatureMeasurement>> call, Response<List<TemperatureMeasurement>> response) {

                if (response.isSuccessful()){

                    temperatureMeasurementMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<TemperatureMeasurement>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Temperature by eui :(");

            }
        });


        return temperatureMeasurementMutableLiveData;

    }


    public LiveData<List<HumidityMeasurement>> getHumidityMeasurementsByUserIdAndEui(String userid, String eui){

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<HumidityMeasurement>> call = terrariumApi.getHumidityMeasurementByUserId(userid, eui);

        call.enqueue(new Callback<List<HumidityMeasurement>>() {
            @Override
            public void onResponse(Call<List<HumidityMeasurement>> call, Response<List<HumidityMeasurement>> response) {

                if (response.isSuccessful()){

                    humidityMeasurementMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<HumidityMeasurement>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Humidity by eui :(");

            }
        });


        return humidityMeasurementMutableLiveData;

    }



    public LiveData<List<Co2Measurement>> getCo2MeasurementsByUserIdAndEui(String userid, String eui){

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Co2Measurement>> call = terrariumApi.getCo2MeasurementByUserId(userid, eui);

        call.enqueue(new Callback<List<Co2Measurement>>() {
            @Override
            public void onResponse(Call<List<Co2Measurement>> call, Response<List<Co2Measurement>> response) {

                if (response.isSuccessful()){

                    co2MeasurementMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Co2Measurement>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Co2 by eui :(");

            }
        });


        return co2MeasurementMutableLiveData;

    }













}
