package com.abdu.and_sep4.View.Humidity;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.WebOrLocale.MeasurementRepo.MeasurementWebOrLocaleRepository;
import com.abdu.and_sep4.Repository.WebOrLocale.MeasurementRepo.MeasurementWebOrLocaleRepositoryImpl;
import com.abdu.and_sep4.Shared.HumidityMeasurement;

import java.util.List;

public class HumidityFragmentViewModel extends AndroidViewModel implements IHumidityFragment {

    private MeasurementWebOrLocaleRepository measurementWebOrLocaleRepository;


    public HumidityFragmentViewModel(Application application) {
        super(application);
        measurementWebOrLocaleRepository = MeasurementWebOrLocaleRepositoryImpl.getInstance(application);

    }


    public LiveData<List<HumidityMeasurement>> getHum(String userId,String eui){
        return  measurementWebOrLocaleRepository.getHumidityMeasurementsByUserIdAndEui(userId, eui);
    }









}
