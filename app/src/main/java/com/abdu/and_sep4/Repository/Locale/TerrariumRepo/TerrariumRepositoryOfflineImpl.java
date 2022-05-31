package com.abdu.and_sep4.Repository.Locale.TerrariumRepo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Dao.TerrariumDao;
import com.abdu.and_sep4.Dao.TerrariumDatabase;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TerrariumRepositoryOfflineImpl implements TerrariumRepositoryOffline {

    private final TerrariumDao terrariumDao;
    private static TerrariumRepositoryOfflineImpl instance;
    private final ExecutorService executorService;

    public TerrariumRepositoryOfflineImpl(Application application) {
        TerrariumDatabase terrariumDatabase = TerrariumDatabase.getInstance(application);
        terrariumDao = terrariumDatabase.terrariumDao();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized TerrariumRepositoryOfflineImpl getInstance(Application application) {

        if (instance == null) {
            instance = new TerrariumRepositoryOfflineImpl(application);
        }

        return instance;
    }


    public LiveData<List<Terrarium>>getTerrariumsByUserId(String userId){
        return terrariumDao.getTerrariumByUserId(userId);
    }





}
