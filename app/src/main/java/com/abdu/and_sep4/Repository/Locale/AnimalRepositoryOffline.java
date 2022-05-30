package com.abdu.and_sep4.Repository.Locale;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Dao.AnimalDao;
import com.abdu.and_sep4.Dao.MeasurementDao;
import com.abdu.and_sep4.Dao.TerrariumDatabase;
import com.abdu.and_sep4.Repository.Web.AnimalRepository;
import com.abdu.and_sep4.Shared.Animal;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalRepositoryOffline {

    private final AnimalDao animalDao;
    private static AnimalRepositoryOffline instance;
    private final ExecutorService executorService;


    public AnimalRepositoryOffline(Application application) {
        TerrariumDatabase terrariumDatabase = TerrariumDatabase.getInstance(application);
        animalDao = terrariumDatabase.animalDao();
        executorService = Executors.newFixedThreadPool(2);
    }


    public static synchronized AnimalRepositoryOffline getInstance(Application application) {

        if (instance == null){
            instance = new AnimalRepositoryOffline(application);
        }

        return instance;
    }



    public LiveData<List<Animal>> getAnimalsByEui(String eui){
        return animalDao.getAnimalsByEui(eui);
    }


}
