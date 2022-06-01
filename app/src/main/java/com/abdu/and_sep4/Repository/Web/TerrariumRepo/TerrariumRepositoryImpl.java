package com.abdu.and_sep4.Repository.Web.TerrariumRepo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.SignalRApi;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Dao.TerrariumDao;
import com.abdu.and_sep4.Dao.TerrariumDatabase;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TerrariumRepositoryImpl implements TerrariumRepository {

    private static TerrariumRepositoryImpl instance;
    private final TerrariumDao terrariumDao;

    private final MutableLiveData<List<Terrarium>> terrariumListMutableLiveData;
    private final MutableLiveData<Terrarium> terrariumMutableLiveData;
    private final MutableLiveData<Terrarium> UpdateterrariumMutableLiveData;
    private final MutableLiveData<List<TemperatureMeasurement>> temperatureMeasurementMutableLiveData;
    private final ExecutorService executorService;
    private final SignalRApi signalRApii;


    private TerrariumRepositoryImpl(Application application) {
        TerrariumDatabase terrariumDatabase = TerrariumDatabase.getInstance(application);
        terrariumDao = terrariumDatabase.terrariumDao();
        terrariumListMutableLiveData = new MutableLiveData<>();
        terrariumMutableLiveData = new MutableLiveData<>();
        UpdateterrariumMutableLiveData = new MutableLiveData<>();
        temperatureMeasurementMutableLiveData = new MutableLiveData<>();
        signalRApii = SignalRApi.getInstance();
        executorService = Executors.newFixedThreadPool(2);


    }

    public static synchronized TerrariumRepositoryImpl getInstance(Application application) {

        if (instance == null) {
            instance = new TerrariumRepositoryImpl(application);
        }

        return instance;
    }


    public LiveData<List<Terrarium>> getTerrariumByUserId(String userid) {

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Terrarium>> call = terrariumApi.getTerrariumByUserId(userid);

        call.enqueue(new Callback<List<Terrarium>>() {
            @Override
            public void onResponse(Call<List<Terrarium>> call, Response<List<Terrarium>> response) {

                if (response.isSuccessful()) {
                    for (Terrarium t : response.body()) {
                        executorService.execute(() -> terrariumDao.addTerrarium(t));
                    }
                    terrariumListMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Terrarium>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Terrarium by user id :(");

            }
        });

        return terrariumListMutableLiveData;

    }

    public LiveData<Terrarium> addTerrarium(Terrarium terrarium) {
        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Terrarium> call = terrariumApi.addTerrarium(terrarium);

        call.enqueue(new Callback<Terrarium>() {
            @Override
            public void onResponse(Call<Terrarium> call, Response<Terrarium> response) {
                if (response.isSuccessful()) {
                    terrariumMutableLiveData.setValue(response.body());
                    Log.e("Retrofit", "working adding terrarium :(");

                }
            }

            @Override
            public void onFailure(Call<Terrarium> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong adding Terrarium :(");
            }
        });


        return terrariumMutableLiveData;

    }


    public LiveData<Terrarium> updateTerrarium(String eui, Terrarium terrarium) {

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Terrarium> call = terrariumApi.updateTerrarium(eui, terrarium);

        call.enqueue(new Callback<Terrarium>() {
            @Override
            public void onResponse(Call<Terrarium> call, Response<Terrarium> response) {
                if (response.isSuccessful()) {
                    UpdateterrariumMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<Terrarium> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong updating Terrarium :(");
            }
        });

        return UpdateterrariumMutableLiveData;


    }

    public void feedAnimal(String eui){
        signalRApii.feedAnimal(eui);
    }



}
