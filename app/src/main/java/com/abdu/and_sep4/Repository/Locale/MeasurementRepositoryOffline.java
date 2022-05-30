package com.abdu.and_sep4.Repository.Locale;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Dao.MeasurementDao;
import com.abdu.and_sep4.Dao.TerrariumDao;
import com.abdu.and_sep4.Dao.TerrariumDatabase;
import com.abdu.and_sep4.Repository.Web.MeasurementsRepository;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MeasurementRepositoryOffline {

    private final MeasurementDao measurementDao;
    private static MeasurementRepositoryOffline instance;
    private final ExecutorService executorService;


    public MeasurementRepositoryOffline(Application application) {
        TerrariumDatabase terrariumDatabase = TerrariumDatabase.getInstance(application);
        measurementDao = terrariumDatabase.measurementDao();
        executorService = Executors.newFixedThreadPool(2);
    }


    public static synchronized MeasurementRepositoryOffline getInstance(Application application) {

        if (instance == null) {
            instance = new MeasurementRepositoryOffline(application);
        }

        return instance;
    }

    public LiveData<List<TemperatureMeasurement>> getTempMeasurementByEui(String eui){
        return measurementDao.getTempMeasurementByEui(eui);
    }

    public LiveData<List<HumidityMeasurement>> getHumidityMeasurementByEui(String eui){
        return measurementDao.getHumidityMeasurementByEui(eui);
    }

    public LiveData<List<Co2Measurement>> getCo2MeasurementByEui(String eui){
        return measurementDao.getCo2MeasurementByEui(eui);
    }





}
