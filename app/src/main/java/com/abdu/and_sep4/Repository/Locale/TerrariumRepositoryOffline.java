package com.abdu.and_sep4.Repository.Locale;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Dao.TerrariumDao;
import com.abdu.and_sep4.Dao.TerrariumDatabase;
import com.abdu.and_sep4.Repository.Web.TerrariumRepository;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TerrariumRepositoryOffline {

    private final TerrariumDao terrariumDao;
    private static TerrariumRepositoryOffline instance;
    private final ExecutorService executorService;

    public TerrariumRepositoryOffline(Application application) {
        TerrariumDatabase terrariumDatabase = TerrariumDatabase.getInstance(application);
        terrariumDao = terrariumDatabase.terrariumDao();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized TerrariumRepositoryOffline getInstance(Application application) {

        if (instance == null) {
            instance = new TerrariumRepositoryOffline(application);
        }

        return instance;
    }


    public LiveData<List<Terrarium>>getTerrariumsByUserId(String userId){
        return terrariumDao.getTerrariumByUserId(userId);
    }





}
