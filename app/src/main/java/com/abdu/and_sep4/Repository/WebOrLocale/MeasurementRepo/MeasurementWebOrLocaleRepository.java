package com.abdu.and_sep4.Repository.WebOrLocale.MeasurementRepo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;

public interface MeasurementWebOrLocaleRepository {

    boolean ifNetworkIsAvailable();

    LiveData<List<TemperatureMeasurement>> getTemperatureMeasurementsByUserIdAndEui(String userid, String eui);

    LiveData<List<HumidityMeasurement>> getHumidityMeasurementsByUserIdAndEui(String userid, String eui);

    LiveData<List<Co2Measurement>> getCo2MeasurementsByUserIdAndEui(String userid, String eui);
}
