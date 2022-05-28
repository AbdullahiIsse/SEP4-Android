package com.abdu.and_sep4.API;

import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;

public interface HumidityReceivedListener {
    void onHumidityReceived(MutableLiveData<List<HumidityMeasurement>> humidityMeasurement);
}
