package com.abdu.and_sep4.Repository.Web.MeasurementsRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;

public interface MeasurementsRepository {

    MutableLiveData<List<TemperatureMeasurement>> getTemperatureMeasurementMutableLiveData();

    MutableLiveData<List<HumidityMeasurement>> getHumidityMeasurementMutableLiveData();

    LiveData<List<TemperatureMeasurement>> getTemperatureMeasurementsByUserIdAndEui(String userid, String eui);

    LiveData<List<HumidityMeasurement>> getHumidityMeasurementsByUserIdAndEui(String userid, String eui);

    LiveData<List<Co2Measurement>> getCo2MeasurementsByUserIdAndEui(String userid, String eui);
}
