package com.abdu.and_sep4.View.CO2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.MeasurementsRepository;
import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;

import java.util.List;

public class Co2FragmentViewModel extends AndroidViewModel {

    private MeasurementsRepository measurementsRepository;


    public Co2FragmentViewModel(Application application) {
        super(application);
        measurementsRepository = MeasurementsRepository.getInstance(application);
        ;
    }


    public LiveData<List<Co2Measurement>> getCo2(String userId,String eui){
        return   measurementsRepository.getCo2MeasurementsByUserIdAndEui(userId, eui);
    }






}
