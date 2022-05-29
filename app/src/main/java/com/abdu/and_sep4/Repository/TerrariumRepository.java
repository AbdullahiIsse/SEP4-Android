package com.abdu.and_sep4.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.API.TerrariumReceivedListener;
import com.abdu.and_sep4.API.TerrariumSignalRApi;
import com.abdu.and_sep4.Shared.Animal;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.Shared.TerrariumV2;
import com.abdu.and_sep4.Shared.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TerrariumRepository {

    private static TerrariumRepository instance;

    private final MutableLiveData<List<Terrarium>> terrariumListMutableLiveData;
    private final MutableLiveData<Terrarium> terrariumMutableLiveData;
    private final MutableLiveData<Terrarium> UpdateterrariumMutableLiveData;
    private final MutableLiveData<List<TemperatureMeasurement>> temperatureMeasurementMutableLiveData;

    private final TerrariumSignalRApi terrariumSignalRApi;

    public TerrariumRepository() {
        terrariumListMutableLiveData = new MutableLiveData<>();
        terrariumMutableLiveData = new MutableLiveData<>();
        UpdateterrariumMutableLiveData = new MutableLiveData<>();
        terrariumSignalRApi = TerrariumSignalRApi.getInstance();
        temperatureMeasurementMutableLiveData = new MutableLiveData<>();


    }

    public static synchronized TerrariumRepository getInstance() {

        if (instance == null) {
            instance = new TerrariumRepository();
        }

        return instance;
    }

    public MutableLiveData<List<Terrarium>> getTerrariumListMutableLiveData() {
        return terrariumListMutableLiveData;
    }

    public MutableLiveData<List<TemperatureMeasurement>> getTemperatureFromSignalR(com.abdu.and_sep4.API.Callback callback, String eui) {
        return terrariumSignalRApi.getTerrariumTemperatureByEui(temperatureMeasurement -> {
            callback.call();
        }, eui);

    }

    public MutableLiveData<List<HumidityMeasurement>> getHumidityFromSignalR(com.abdu.and_sep4.API.Callback callback, String eui) {
        return terrariumSignalRApi.getTerrariumHumidityByEui(humidityMeasurement -> {
            callback.call();
        }, eui);

    }

    public MutableLiveData<List<Co2Measurement>> getCo2FromSignalR(com.abdu.and_sep4.API.Callback callback, String eui) {
        return terrariumSignalRApi.getTerrariumCo2ByEui(co2Measurement -> {
            callback.call();
        }, eui);


    }

    public MutableLiveData<List<TerrariumV2>> getTerrariumByUserIdFromSignalR(com.abdu.and_sep4.API.Callback callback, String userId) {
        return terrariumSignalRApi.getTerrariumByUserId(terrarium -> {
            callback.call();
        }, userId);
    }

    public MutableLiveData<TerrariumV2> addTerrarium(com.abdu.and_sep4.API.Callback callback, TerrariumV2 terrarium) {
        return terrariumSignalRApi.addTerrarium(terrarium1 -> {
            callback.call();
        },terrarium);
    }


    public MutableLiveData<List<Animal>> getAnimalFromSignalR(com.abdu.and_sep4.API.Callback callback, String eui) {
        return terrariumSignalRApi.getAnimalByEui(animal -> {
            callback.call();
        },eui);
    }

    public MutableLiveData<Animal> addAnimal(com.abdu.and_sep4.API.Callback callback, Animal animal) {
        return terrariumSignalRApi.AddAnimalToDb(animal,addAnimal -> {
            callback.call();
        });
    }

    public MutableLiveData<User> addUser(com.abdu.and_sep4.API.Callback callback, User user) {
        return terrariumSignalRApi.addUser(addUser -> {
            callback.call();
        },user);
    }


    public LiveData<List<Terrarium>> getTerrariumByUserId(String userid) {

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Terrarium>> call = terrariumApi.getTerrariumByUserId(userid);

        call.enqueue(new Callback<List<Terrarium>>() {
            @Override
            public void onResponse(Call<List<Terrarium>> call, Response<List<Terrarium>> response) {

                if (response.isSuccessful()) {

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

    public void deleteTerrarium(long id) {
        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Terrarium> call = terrariumApi.deleteTerrarium(id);

        call.enqueue(new Callback<Terrarium>() {
            @Override
            public void onResponse(Call<Terrarium> call, Response<Terrarium> response) {
                if (response.isSuccessful()) {
                    Log.e("Retrofit", "deleting Terrarium successfully");
                }
            }

            @Override
            public void onFailure(Call<Terrarium> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong deleting terrarium :(");
            }
        });
    }


    public LiveData<Terrarium> updateTerrarium(long id, Terrarium terrarium) {

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Terrarium> call = terrariumApi.updateTerrarium(id, terrarium);

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


}
