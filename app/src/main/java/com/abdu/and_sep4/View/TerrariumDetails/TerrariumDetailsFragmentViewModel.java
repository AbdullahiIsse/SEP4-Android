package com.abdu.and_sep4.View.TerrariumDetails;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Web.MeasurementsRepository;
import com.abdu.and_sep4.Repository.WebOrLocale.MeasurementWebOrLocaleRepository;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;


import java.util.List;

public class TerrariumDetailsFragmentViewModel extends AndroidViewModel {

    private MeasurementWebOrLocaleRepository measurementWebOrLocaleRepository;
    private LiveData<List<TemperatureMeasurement>> terrariumLiveData;



    public TerrariumDetailsFragmentViewModel(Application application) {
        super(application);
        measurementWebOrLocaleRepository = MeasurementWebOrLocaleRepository.getInstance(application);


    }


    public LiveData<List<TemperatureMeasurement>> getTemperatureByUserIdAndEuiLiveData(String userId,String eui) {
        return measurementWebOrLocaleRepository.getTemperatureMeasurementsByUserIdAndEui(userId, eui);
    }



















}
