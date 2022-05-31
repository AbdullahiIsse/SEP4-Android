package com.abdu.and_sep4.Repository.Locale.MeassurementRepo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;

public interface MeasurementRepositoryOffline {

    LiveData<List<TemperatureMeasurement>> getTempMeasurementByEui(String eui);

    LiveData<List<HumidityMeasurement>> getHumidityMeasurementByEui(String eui);

    LiveData<List<Co2Measurement>> getCo2MeasurementByEui(String eui);

}
