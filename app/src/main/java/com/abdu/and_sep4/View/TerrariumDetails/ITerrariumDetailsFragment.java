package com.abdu.and_sep4.View.TerrariumDetails;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;

public interface ITerrariumDetailsFragment {

    LiveData<List<TemperatureMeasurement>> getTemperatureByUserIdAndEuiLiveData(String userId, String eui);

    void feedAnimal(String eui);
}
