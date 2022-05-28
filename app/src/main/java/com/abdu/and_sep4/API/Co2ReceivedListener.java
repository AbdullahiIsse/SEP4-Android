package com.abdu.and_sep4.API;

import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;

public interface Co2ReceivedListener {
    void onCo2Received(MutableLiveData<List<Co2Measurement>> co2Measurement);
}
