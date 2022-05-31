package com.abdu.and_sep4.View.TerrariumDetails;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Web.TerrariumRepo.TerrariumRepository;
import com.abdu.and_sep4.Repository.Web.TerrariumRepo.TerrariumRepositoryImpl;
import com.abdu.and_sep4.Repository.WebOrLocale.MeasurementRepo.MeasurementWebOrLocaleRepository;
import com.abdu.and_sep4.Repository.WebOrLocale.MeasurementRepo.MeasurementWebOrLocaleRepositoryImpl;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;


import java.util.List;

public class TerrariumDetailsFragmentViewModel extends AndroidViewModel implements ITerrariumDetailsFragment {

    private MeasurementWebOrLocaleRepository measurementWebOrLocaleRepository;
    private TerrariumRepository terrariumRepository;
    private LiveData<List<TemperatureMeasurement>> terrariumLiveData;



    public TerrariumDetailsFragmentViewModel(Application application) {
        super(application);
        measurementWebOrLocaleRepository = MeasurementWebOrLocaleRepositoryImpl.getInstance(application);
        terrariumRepository = TerrariumRepositoryImpl.getInstance(application);


    }


    public LiveData<List<TemperatureMeasurement>> getTemperatureByUserIdAndEuiLiveData(String userId,String eui) {
        return measurementWebOrLocaleRepository.getTemperatureMeasurementsByUserIdAndEui(userId, eui);
    }

    public void feedAnimal(String eui){
        terrariumRepository.feedAnimal(eui);
    }



















}
