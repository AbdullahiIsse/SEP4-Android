package com.abdu.and_sep4.API;

import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;

public interface TemperatureReceivedListener {
    void onTempReceived(MutableLiveData<List<TemperatureMeasurement>> temperatureMeasurement);





}
