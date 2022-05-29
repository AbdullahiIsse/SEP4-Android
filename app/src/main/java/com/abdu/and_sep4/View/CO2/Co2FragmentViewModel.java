package com.abdu.and_sep4.View.CO2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;

import java.util.List;

public class Co2FragmentViewModel extends ViewModel {

    private TerrariumRepository terrariumRepository;
    private final MutableLiveData<Boolean> loading;

    public Co2FragmentViewModel() {
        terrariumRepository = TerrariumRepository.getInstance();
        loading = new MutableLiveData<>(true);
    }


    public MutableLiveData<List<Co2Measurement>> getCo2(String eui){
        return   terrariumRepository.getCo2FromSignalR(() -> loading.setValue(false),eui);
    }


    public LiveData<Boolean> loading() {
        return loading;
    }



}
