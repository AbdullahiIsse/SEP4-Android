package com.abdu.and_sep4.API;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.Shared.Animal;
import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.FoodDispenser;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.TerrariumV2;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Single;

public class TerrariumSignalRApi {

    private static TerrariumSignalRApi instance;
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    private HubConnection hubConnection;
    private ArrayList<TemperatureMeasurement> temperatureMeasurements = new ArrayList<>();
    private ArrayList<HumidityMeasurement> humidityMeasurements = new ArrayList<>();
    private ArrayList<Co2Measurement> co2Measurements = new ArrayList<>();
    private ArrayList<TerrariumV2> terrarium = new ArrayList<>();
    private ArrayList<Animal> animal = new ArrayList<>();
    private final MutableLiveData<List<TemperatureMeasurement>> terrariumTemperatureByEuiMutableLiveData;
    private final MutableLiveData<List<HumidityMeasurement>> terrariumHumidityByEuiMutableLiveData;
    private final MutableLiveData<List<Co2Measurement>> terrariumCo2ByEuiMutableLiveData;
    private final MutableLiveData<List<TerrariumV2>> terrariumByUserIdMutableLiveData;
    private final MutableLiveData<List<Animal>> animalByEuiMutableLiveData;

    private final MutableLiveData<TerrariumV2> terrariumV2MutableLiveData;
    private final MutableLiveData<Animal> animalMutableLiveData;


    public static TerrariumSignalRApi getInstance() {
        if (instance == null) {
            instance = new TerrariumSignalRApi();
        }
        return instance;
    }


    public TerrariumSignalRApi() {
        terrariumTemperatureByEuiMutableLiveData = new MutableLiveData<>();
        terrariumHumidityByEuiMutableLiveData = new MutableLiveData<>();
        terrariumCo2ByEuiMutableLiveData = new MutableLiveData<>();
        terrariumByUserIdMutableLiveData = new MutableLiveData<>();
        animalByEuiMutableLiveData = new MutableLiveData<>();
        animalMutableLiveData = new MutableLiveData<>();
        terrariumV2MutableLiveData = new MutableLiveData<>();
    }


    public MutableLiveData<List<TemperatureMeasurement>> getTerrariumTemperatureByEui(TemperatureReceivedListener listener, String eui) {
        executorService.execute(() -> {
            hubConnection = HubConnectionBuilder.create("https://terraeyes.azurewebsites.net/AppHub").build();

            hubConnection.start().blockingAwait();

            Single<List> temperatureDataFromDataToAndroid = hubConnection.invoke(List.class, "TerrariumTemperatureDataFromDataToAndroid", eui);

            Log.e("signalr", temperatureDataFromDataToAndroid.blockingGet().toString());

            String json = new Gson().toJson(temperatureDataFromDataToAndroid.blockingGet());

            Gson gson = new Gson();

            Type temp = new TypeToken<ArrayList<TemperatureMeasurement>>() {
            }.getType();

            temperatureMeasurements = gson.fromJson(json, temp);

            Log.e("gson", temperatureMeasurements.toString());

            terrariumTemperatureByEuiMutableLiveData.postValue(temperatureMeasurements);

            mainThreadHandler.post(() -> listener.onTempReceived(terrariumTemperatureByEuiMutableLiveData));
            hubConnection.close();

        });

        return terrariumTemperatureByEuiMutableLiveData;
    }


    public MutableLiveData<List<HumidityMeasurement>> getTerrariumHumidityByEui(HumidityReceivedListener listener, String eui) {
        executorService.execute(() -> {
            hubConnection = HubConnectionBuilder.create("https://terraeyes.azurewebsites.net/AppHub").build();

            hubConnection.start().blockingAwait();

            Single<List> humidityDataFromDataToAndroid = hubConnection.invoke(List.class, "TerrariumHumidityDataFromDataToAndroid", eui);

            Log.e("signalr", humidityDataFromDataToAndroid.blockingGet().toString());

            String json = new Gson().toJson(humidityDataFromDataToAndroid.blockingGet());

            Gson gson = new Gson();

            Type temp = new TypeToken<ArrayList<HumidityMeasurement>>() {
            }.getType();

            humidityMeasurements = gson.fromJson(json, temp);

            Log.e("gson", humidityMeasurements.toString());

            terrariumHumidityByEuiMutableLiveData.postValue(humidityMeasurements);

            mainThreadHandler.post(() -> listener.onHumidityReceived(terrariumHumidityByEuiMutableLiveData));
            hubConnection.close();

        });

        return terrariumHumidityByEuiMutableLiveData;

    }


    public MutableLiveData<List<Co2Measurement>> getTerrariumCo2ByEui(Co2ReceivedListener listener, String eui) {
        executorService.execute(() -> {
            hubConnection = HubConnectionBuilder.create("https://terraeyes.azurewebsites.net/AppHub").build();

            hubConnection.start().blockingAwait();

            Single<List> co2DataFromDataToAndroid = hubConnection.invoke(List.class, "TerrariumCarbonDataFromDataToAndroid", eui);

            Log.e("signalr", co2DataFromDataToAndroid.blockingGet().toString());

            String json = new Gson().toJson(co2DataFromDataToAndroid.blockingGet());

            Gson gson = new Gson();

            Type temp = new TypeToken<ArrayList<Co2Measurement>>() {
            }.getType();

            co2Measurements = gson.fromJson(json, temp);

            Log.e("gson", co2Measurements.toString());

            terrariumCo2ByEuiMutableLiveData.postValue(co2Measurements);

            mainThreadHandler.post(() -> listener.onCo2Received(terrariumCo2ByEuiMutableLiveData));

            hubConnection.close();

        });

        return terrariumCo2ByEuiMutableLiveData;

    }

    public MutableLiveData<List<TerrariumV2>> getTerrariumByUserId(TerrariumReceivedListener listener, String userId) {
        executorService.execute(() -> {
            hubConnection = HubConnectionBuilder.create("https://terraeyes.azurewebsites.net/AppHub").build();

            hubConnection.start().blockingAwait();

            Single<List> terrariumByUserDataFromDataToAndroid = hubConnection.invoke(List.class, "UsersTerrariumDataFromDataToAndroid", userId);

            Log.e("signalr", terrariumByUserDataFromDataToAndroid.blockingGet().toString());

            String json = new Gson().toJson(terrariumByUserDataFromDataToAndroid.blockingGet());

            Gson gson = new Gson();

            Type temp = new TypeToken<ArrayList<TerrariumV2>>() {
            }.getType();

            terrarium = gson.fromJson(json, temp);

            Log.e("gson", terrarium.toString());

            terrariumByUserIdMutableLiveData.postValue(terrarium);

            mainThreadHandler.post(() -> listener.onTerrariumReceived(terrariumByUserIdMutableLiveData));

            hubConnection.close();

        });

        return terrariumByUserIdMutableLiveData;

    }

    public MutableLiveData<List<Animal>> getAnimalByEui(AnimalReceivedListener listener, String eui) {
        executorService.execute(() -> {
            hubConnection = HubConnectionBuilder.create("https://terraeyes.azurewebsites.net/AppHub").build();

            hubConnection.start().blockingAwait();

            Single<List> animalByEuiFromDataToAndroid = hubConnection.invoke(List.class, "TerrariumAnimalsDataFromDataToAndroid", eui);

            Log.e("signalr", animalByEuiFromDataToAndroid.blockingGet().toString());

            String json = new Gson().toJson(animalByEuiFromDataToAndroid.blockingGet());

            Gson gson = new Gson();

            Type temp = new TypeToken<ArrayList<Animal>>() {
            }.getType();

            animal = gson.fromJson(json, temp);

            Log.e("gson", animal.toString());

            animalByEuiMutableLiveData.postValue(animal);

            mainThreadHandler.post(() -> listener.onAnimalReceived(animalByEuiMutableLiveData));

            hubConnection.close();

        });

        return animalByEuiMutableLiveData;

    }

    public MutableLiveData<Animal> AddAnimalToDb(Animal animal, AnimalAddListener listener) {
        executorService.execute(() -> {
            hubConnection = HubConnectionBuilder.create("https://terraeyes.azurewebsites.net/AppHub").build();

            hubConnection.start().blockingAwait();

            hubConnection.invoke("AddAnimalToDb", animal);

            hubConnection.send("AddAnimalToDb", animal);

            mainThreadHandler.post(() -> listener.AnimalAddListener(animalMutableLiveData));

            hubConnection.close();

        });

        return animalMutableLiveData;

    }


    public MutableLiveData<TerrariumV2> addTerrarium(TerrariumAddListener listener, TerrariumV2 terrarium) {
        executorService.execute(() -> {
            hubConnection = HubConnectionBuilder.create("https://terraeyes.azurewebsites.net/AppHub").build();

            hubConnection.start().blockingAwait();

            Single<TerrariumV2> addTerrariumToDb = hubConnection.invoke(TerrariumV2.class, "AddTerrariumToDb", terrarium);

            Log.e("signalr", addTerrariumToDb.blockingGet().toString());

            terrariumV2MutableLiveData.postValue(addTerrariumToDb.blockingGet());

            mainThreadHandler.post(() -> listener.onTerrariumAdded(terrariumV2MutableLiveData));

            hubConnection.close();

        });

        return terrariumV2MutableLiveData;

    }



}
