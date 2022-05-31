package com.abdu.and_sep4.View.Humidity;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.HumidityMeasurement;

import java.util.List;

public interface IHumidityFragment {

    LiveData<List<HumidityMeasurement>> getHum(String userId, String eui);
}
