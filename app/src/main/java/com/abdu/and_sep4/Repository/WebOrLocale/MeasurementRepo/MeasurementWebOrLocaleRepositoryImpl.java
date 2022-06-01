package com.abdu.and_sep4.Repository.WebOrLocale.MeasurementRepo;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Locale.MeassurementRepo.MeasurementRepositoryOfflineImpl;
import com.abdu.and_sep4.Repository.Web.MeasurementsRepo.MeasurementsRepositoryImpl;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;

public class MeasurementWebOrLocaleRepositoryImpl implements MeasurementWebOrLocaleRepository{

    private static MeasurementWebOrLocaleRepositoryImpl instance;
    private final MeasurementRepositoryOfflineImpl measurementRepositoryOfflineImpl;
    private final MeasurementsRepositoryImpl measurementsRepositoryImpl;
    private Application application;

    private MeasurementWebOrLocaleRepositoryImpl(Application application) {
        measurementRepositoryOfflineImpl = MeasurementRepositoryOfflineImpl.getInstance(application);
        measurementsRepositoryImpl = MeasurementsRepositoryImpl.getInstance(application);
        this.application = application;

    }


    public static synchronized MeasurementWebOrLocaleRepositoryImpl getInstance(Application application) {

        if (instance == null) {
            instance = new MeasurementWebOrLocaleRepositoryImpl(application);
        }

        return instance;
    }


    public boolean ifNetworkIsAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null) {
            if (info.isConnected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }



    public LiveData<List<TemperatureMeasurement>> getTemperatureMeasurementsByUserIdAndEui(String userid, String eui){
        if (ifNetworkIsAvailable()) {
            return measurementsRepositoryImpl.getTemperatureMeasurementsByUserIdAndEui(userid,eui);
        } else {
            return measurementRepositoryOfflineImpl.getTempMeasurementByEui(eui);
        }

    }

    public LiveData<List<HumidityMeasurement>> getHumidityMeasurementsByUserIdAndEui(String userid, String eui){
        if (ifNetworkIsAvailable()) {
            return measurementsRepositoryImpl.getHumidityMeasurementsByUserIdAndEui(userid, eui);
        } else {
            return measurementRepositoryOfflineImpl.getHumidityMeasurementByEui(eui);
        }

    }

    public LiveData<List<Co2Measurement>> getCo2MeasurementsByUserIdAndEui(String userid, String eui){
        if (ifNetworkIsAvailable()) {
            return measurementsRepositoryImpl.getCo2MeasurementsByUserIdAndEui(userid,eui);
        } else {
            return measurementRepositoryOfflineImpl.getCo2MeasurementByEui(eui);
        }

    }
















}
