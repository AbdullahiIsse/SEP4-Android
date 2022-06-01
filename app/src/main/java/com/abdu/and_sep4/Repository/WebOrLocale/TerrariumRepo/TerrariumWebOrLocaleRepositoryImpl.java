package com.abdu.and_sep4.Repository.WebOrLocale.TerrariumRepo;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Locale.TerrariumRepo.TerrariumRepositoryOfflineImpl;
import com.abdu.and_sep4.Repository.Web.TerrariumRepo.TerrariumRepositoryImpl;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

public class TerrariumWebOrLocaleRepositoryImpl implements TerrariumWebOrLocaleRepository {

    private static TerrariumWebOrLocaleRepositoryImpl instance;
    private final TerrariumRepositoryOfflineImpl terrariumRepositoryOfflineImpl;
    private final TerrariumRepositoryImpl terrariumRepositoryImpl;
    private Application application;

    private TerrariumWebOrLocaleRepositoryImpl(Application application) {
        terrariumRepositoryOfflineImpl = TerrariumRepositoryOfflineImpl.getInstance(application);
        terrariumRepositoryImpl = TerrariumRepositoryImpl.getInstance(application);
        this.application = application;
    }


    public static synchronized TerrariumWebOrLocaleRepositoryImpl getInstance(Application application) {

        if (instance == null) {
            instance = new TerrariumWebOrLocaleRepositoryImpl(application);
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
            return terrariumRepositoryImpl.getTerrariumByUserId(userid);
        } else {
            return terrariumRepositoryOfflineImpl.getTerrariumsByUserId(userid);
        }

    }


}
