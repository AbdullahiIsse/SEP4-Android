package com.abdu.and_sep4.View.TerrariumDetails;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.MeasurementsRepository;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;


import java.util.List;

public class TerrariumDetailsFragmentViewModel extends AndroidViewModel {

    private MeasurementsRepository measurementsRepository;
    private LiveData<List<TemperatureMeasurement>> terrariumLiveData;



    public TerrariumDetailsFragmentViewModel(Application application) {
        super(application);
        measurementsRepository = MeasurementsRepository.getInstance(application);


    }


    public LiveData<List<TemperatureMeasurement>> getTemperatureByUserIdAndEuiLiveData(String userId,String eui) {
        return measurementsRepository.getTemperatureMeasurementsByUserIdAndEui(userId,eui);
    }



















}
