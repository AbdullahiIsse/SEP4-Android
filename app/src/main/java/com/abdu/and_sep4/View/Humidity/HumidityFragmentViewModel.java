package com.abdu.and_sep4.View.Humidity;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Web.MeasurementsRepository;
import com.abdu.and_sep4.Repository.WebOrLocale.MeasurementWebOrLocaleRepository;
import com.abdu.and_sep4.Shared.HumidityMeasurement;

import java.util.List;

public class HumidityFragmentViewModel extends AndroidViewModel {

    private MeasurementWebOrLocaleRepository measurementWebOrLocaleRepository;


    public HumidityFragmentViewModel(Application application) {
        super(application);
        measurementWebOrLocaleRepository = MeasurementWebOrLocaleRepository.getInstance(application);

    }


    public LiveData<List<HumidityMeasurement>> getHum(String userId,String eui){
        return  measurementWebOrLocaleRepository.getHumidityMeasurementsByUserIdAndEui(userId, eui);
    }









}
