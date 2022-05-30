package com.abdu.and_sep4.Repository.WebOrLocale;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Locale.TerrariumRepositoryOffline;
import com.abdu.and_sep4.Repository.Web.TerrariumRepository;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TerrariumWebOrLocaleRepository {

    private static TerrariumWebOrLocaleRepository instance;
    private final TerrariumRepositoryOffline terrariumRepositoryOffline;
    private final TerrariumRepository terrariumRepository;
    private Application application;

    public TerrariumWebOrLocaleRepository(Application application) {
        terrariumRepositoryOffline = TerrariumRepositoryOffline.getInstance(application);
        terrariumRepository = TerrariumRepository.getInstance(application);
        this.application = application;
    }


    public static synchronized TerrariumWebOrLocaleRepository getInstance(Application application) {

        if (instance == null) {
            instance = new TerrariumWebOrLocaleRepository(application);
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


    public LiveData<List<Terrarium>> getTerrariumsByUserId(String userid) {
        if (ifNetworkIsAvailable()) {
            return terrariumRepository.getTerrariumByUserId(userid);
        } else {
            return terrariumRepositoryOffline.getTerrariumsByUserId(userid);
        }

    }


}
