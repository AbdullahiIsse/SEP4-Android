package com.abdu.and_sep4.View.TerrariumDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.FoodRepository;
import com.abdu.and_sep4.Repository.MeasurementsRepository;
import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.FoodDispenser;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.Measurements;
import com.abdu.and_sep4.Shared.Temperatur;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;


import java.util.ArrayList;
import java.util.List;

public class TerrariumDetailsFragmentViewModel extends ViewModel {

    private MeasurementsRepository measurementsRepository;
    private TerrariumRepository terrariumRepository;
    private FoodRepository foodRepository;

    private LiveData<List<Measurements>> terrariumLiveData;
    private final MutableLiveData<Boolean> loading;


    public TerrariumDetailsFragmentViewModel() {
        measurementsRepository = MeasurementsRepository.getInstance();
        foodRepository = FoodRepository.getInstance();
        terrariumRepository = TerrariumRepository.getInstance();

        terrariumLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>(true);

    }


    public LiveData<List<Measurements>> getTemperatureByTerrariumIdLiveData(int id) {
        return measurementsRepository.getMeasurementsByTerrariumId(id);
    }




    public LiveData<FoodDispenser> addFood(FoodDispenser addFood){
        return foodRepository.addFood(addFood);
    }

    public MutableLiveData<List<TemperatureMeasurement>> getTemp(String eui){
       return   terrariumRepository.getTemperatureFromSignalR(() -> loading.setValue(false),eui);
    }

    public MutableLiveData<List<HumidityMeasurement>> getHum(String eui){
        return   terrariumRepository.getHumidityFromSignalR(() -> loading.setValue(false),eui);
    }

    public MutableLiveData<List<Co2Measurement>> getCo2(String eui){
        return   terrariumRepository.getCo2FromSignalR(() -> loading.setValue(false),eui);
    }

    public LiveData<Boolean> loading() {
        return loading;
    }














}
