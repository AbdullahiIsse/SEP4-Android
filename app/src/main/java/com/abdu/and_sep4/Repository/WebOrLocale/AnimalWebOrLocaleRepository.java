package com.abdu.and_sep4.Repository.WebOrLocale;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Locale.AnimalRepositoryOffline;
import com.abdu.and_sep4.Repository.Locale.MeasurementRepositoryOffline;
import com.abdu.and_sep4.Repository.Web.AnimalRepository;
import com.abdu.and_sep4.Repository.Web.MeasurementsRepository;
import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

public class AnimalWebOrLocaleRepository {


    private static AnimalWebOrLocaleRepository instance;
    private final AnimalRepositoryOffline animalRepositoryOffline;
    private final AnimalRepository animalRepository;
    private Application application;

    public AnimalWebOrLocaleRepository(Application application) {
        animalRepositoryOffline = AnimalRepositoryOffline.getInstance(application);
        animalRepository = AnimalRepository.getInstance(application);
        this.application = application;
    }

    public static synchronized AnimalWebOrLocaleRepository getInstance(Application application) {

        if (instance == null) {
            instance = new AnimalWebOrLocaleRepository(application);
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

    public LiveData<List<Animal>> getAllAnimalsByUserId(String userId, String eui){
        if (ifNetworkIsAvailable()) {
            return animalRepository.getAllAnimalsByUserId(userId, eui);
        } else {
            return animalRepositoryOffline.getAnimalsByEui(eui);
        }
    }








}
