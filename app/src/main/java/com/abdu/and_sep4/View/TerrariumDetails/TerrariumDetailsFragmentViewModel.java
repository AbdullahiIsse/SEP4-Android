package com.abdu.and_sep4.View.TerrariumDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.abdu.and_sep4.Repository.MeasurementsRepository;
import com.abdu.and_sep4.Shared.Measurements;
import com.abdu.and_sep4.Shared.Temperatur;


import java.util.List;

public class TerrariumDetailsFragmentViewModel extends ViewModel {

    private MeasurementsRepository measurementsRepository;

    private LiveData<List<Measurements>> terrariumLiveData;


    public TerrariumDetailsFragmentViewModel() {
        measurementsRepository = MeasurementsRepository.getInstance();

        terrariumLiveData = new MutableLiveData<>();

    }


    public LiveData<List<Measurements>> getTemperatureByTerrariumIdLiveData(int id) {
        return measurementsRepository.getMeasurementsByTerrariumId(id);
    }














}
