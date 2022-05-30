package com.abdu.and_sep4.View.CO2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Web.MeasurementsRepository;
import com.abdu.and_sep4.Repository.WebOrLocale.MeasurementWebOrLocaleRepository;
import com.abdu.and_sep4.Shared.Co2Measurement;

import java.util.List;

public class Co2FragmentViewModel extends AndroidViewModel {

    private MeasurementWebOrLocaleRepository measurementWebOrLocaleRepository;


    public Co2FragmentViewModel(Application application) {
        super(application);
        measurementWebOrLocaleRepository = MeasurementWebOrLocaleRepository.getInstance(application);

    }


    public LiveData<List<Co2Measurement>> getCo2(String userId,String eui){
        return   measurementWebOrLocaleRepository.getCo2MeasurementsByUserIdAndEui(userId, eui);
    }






}
