package com.abdu.and_sep4.View.CO2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.WebOrLocale.MeasurementRepo.MeasurementWebOrLocaleRepository;
import com.abdu.and_sep4.Repository.WebOrLocale.MeasurementRepo.MeasurementWebOrLocaleRepositoryImpl;
import com.abdu.and_sep4.Shared.Co2Measurement;

import java.util.List;

public class Co2FragmentViewModel extends AndroidViewModel implements ICo2Fragment {

    private MeasurementWebOrLocaleRepository measurementWebOrLocaleRepository;


    public Co2FragmentViewModel(Application application) {
        super(application);
        measurementWebOrLocaleRepository = MeasurementWebOrLocaleRepositoryImpl.getInstance(application);

    }


    public LiveData<List<Co2Measurement>> getCo2(String userId,String eui){
        return   measurementWebOrLocaleRepository.getCo2MeasurementsByUserIdAndEui(userId, eui);
    }






}
