package com.abdu.and_sep4.View.Humidity;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.MeasurementsRepository;
import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.HumidityMeasurement;

import java.util.List;

public class HumidityFragmentViewModel extends AndroidViewModel {
    private MeasurementsRepository measurementsRepository;


    public HumidityFragmentViewModel(Application application) {
        super(application);
        measurementsRepository = MeasurementsRepository.getInstance(application);

    }


    public LiveData<List<HumidityMeasurement>> getHum(String userId,String eui){
        return  measurementsRepository.getHumidityMeasurementsByUserIdAndEui(userId, eui);
    }









}
