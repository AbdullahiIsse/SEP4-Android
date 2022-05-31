package com.abdu.and_sep4.Repository.WebOrLocale.AnimalRepo;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Locale.AnimalRepo.AnimalRepositoryOfflineImpl;
import com.abdu.and_sep4.Repository.Web.AnimalRepo.AnimalRepositoryImpl;
import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

public class AnimalWebOrLocaleRepositoryImpl implements AnimalWebOrLocaleRepository {


    private static AnimalWebOrLocaleRepositoryImpl instance;
    private final AnimalRepositoryOfflineImpl animalRepositoryOfflineImpl;
    private final AnimalRepositoryImpl animalRepositoryImpl;
    private Application application;

    public AnimalWebOrLocaleRepositoryImpl(Application application) {
        animalRepositoryOfflineImpl = AnimalRepositoryOfflineImpl.getInstance(application);
        animalRepositoryImpl = AnimalRepositoryImpl.getInstance(application);
        this.application = application;
    }

    public static synchronized AnimalWebOrLocaleRepositoryImpl getInstance(Application application) {

        if (instance == null) {
            instance = new AnimalWebOrLocaleRepositoryImpl(application);
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
            return animalRepositoryImpl.getAllAnimalsByUserId(userId, eui);
        } else {
            return animalRepositoryOfflineImpl.getAnimalsByEui(eui);
        }
    }








}
