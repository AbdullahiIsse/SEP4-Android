package com.abdu.and_sep4.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

@Dao
public interface MeasurementDao {

    @Query("select * from temperatureMeasurement_table where eui = :eui")
    LiveData<List<TemperatureMeasurement>> getTempMeasurementByEui(String eui);


    @Query("select * from humidityMeasurement_table where eui = :eui")
    LiveData<List<HumidityMeasurement>> getHumidityMeasurementByEui(String eui);


    @Query("select * from co2Measurement_table where eui = :eui")
    LiveData<List<Co2Measurement>> getCo2MeasurementByEui(String eui);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTemperatureMeasurement(TemperatureMeasurement temperatureMeasurement);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addHumidityMeasurement(HumidityMeasurement humidityMeasurement);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCo2Measurement(Co2Measurement co2Measurement);


}
