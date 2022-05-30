package com.abdu.and_sep4.Repository.WebOrLocale;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Locale.MeasurementRepositoryOffline;
import com.abdu.and_sep4.Repository.Locale.TerrariumRepositoryOffline;
import com.abdu.and_sep4.Repository.Web.MeasurementsRepository;
import com.abdu.and_sep4.Repository.Web.TerrariumRepository;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;

import java.util.List;

public class MeasurementWebOrLocaleRepository {

    private static MeasurementWebOrLocaleRepository instance;
    private final MeasurementRepositoryOffline measurementRepositoryOffline;
    private final MeasurementsRepository measurementsRepository;
    private Application application;

    public MeasurementWebOrLocaleRepository(Application application) {
        measurementRepositoryOffline = MeasurementRepositoryOffline.getInstance(application);
        measurementsRepository = MeasurementsRepository.getInstance(application);
        this.application = application;

    }


    public static synchronized MeasurementWebOrLocaleRepository getInstance(Application application) {

        if (instance == null) {
            instance = new MeasurementWebOrLocaleRepository(application);
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
            return measurementsRepository.getTemperatureMeasurementsByUserIdAndEui(userid,eui);
        } else {
            return measurementRepositoryOffline.getTempMeasurementByEui(eui);
        }

    }

    public LiveData<List<HumidityMeasurement>> getHumidityMeasurementsByUserIdAndEui(String userid, String eui){
        if (ifNetworkIsAvailable()) {
            return measurementsRepository.getHumidityMeasurementsByUserIdAndEui(userid, eui);
        } else {
            return measurementRepositoryOffline.getHumidityMeasurementByEui(eui);
        }

    }

    public LiveData<List<Co2Measurement>> getCo2MeasurementsByUserIdAndEui(String userid, String eui){
        if (ifNetworkIsAvailable()) {
            return measurementsRepository.getCo2MeasurementsByUserIdAndEui(userid,eui);
        } else {
            return measurementRepositoryOffline.getCo2MeasurementByEui(eui);
        }

    }
















}
