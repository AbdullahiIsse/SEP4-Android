package com.abdu.and_sep4.View.TerrariumDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.abdu.and_sep4.Repository.TemperatureRepository;
import com.abdu.and_sep4.Shared.Temperatur;


import java.util.List;

public class TerrariumDetailsFragmentViewModel extends ViewModel {

    private TemperatureRepository temperatureRepository;

    private LiveData<List<Temperatur>> terrariumLiveData;


    public TerrariumDetailsFragmentViewModel() {
        temperatureRepository = TemperatureRepository.getInstance();

        terrariumLiveData = new MutableLiveData<>();

    }


    public LiveData<List<Temperatur>> getTemperatureByTerrariumIdLiveData( int id) {
        return temperatureRepository.getTemperatureByTerrariumId(id);
    }














}
