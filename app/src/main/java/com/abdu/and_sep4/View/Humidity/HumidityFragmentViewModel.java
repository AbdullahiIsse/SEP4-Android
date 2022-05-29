package com.abdu.and_sep4.View.Humidity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.HumidityMeasurement;

import java.util.List;

public class HumidityFragmentViewModel extends ViewModel {
    private TerrariumRepository terrariumRepository;
    private final MutableLiveData<Boolean> loading;

    public HumidityFragmentViewModel() {
        terrariumRepository = TerrariumRepository.getInstance();
        loading = new MutableLiveData<>(true);
    }


    public MutableLiveData<List<HumidityMeasurement>> getHum(String eui){
        return  terrariumRepository.getHumidityFromSignalR(() -> loading.setValue(false),eui);
    }


    public LiveData<Boolean> loading() {
        return loading;
    }






}
