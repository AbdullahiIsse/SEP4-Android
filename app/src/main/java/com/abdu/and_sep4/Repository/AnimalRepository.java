package com.abdu.and_sep4.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalRepository {

    private static AnimalRepository instance;

    private final MutableLiveData <Animal> addAnimalMutableLiveData;

    private final MutableLiveData<List<Animal>> getAllAnimalsByUserIdMutableLiveData;

    private final MutableLiveData<Animal> updateAnimals;


    public AnimalRepository() {
        addAnimalMutableLiveData = new MutableLiveData<>();
        getAllAnimalsByUserIdMutableLiveData = new MutableLiveData<>();
        updateAnimals = new MutableLiveData<>();

    }


    public static synchronized AnimalRepository getInstance() {

        if (instance == null){
            instance = new AnimalRepository();
        }

        return instance;
    }


    public MutableLiveData<Animal> getAddAnimalMutableLiveData() {
        return addAnimalMutableLiveData;
    }

    public LiveData<List<Animal>> getGetAllAnimalsByUserIdMutableLiveData() {
        return getAllAnimalsByUserIdMutableLiveData;
    }


    public LiveData<Animal> addAnimals(Animal animal) {

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Animal> call = terrariumApi.addAnimal(animal);


        call.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
             if(response.isSuccessful()) {
                 addAnimalMutableLiveData.setValue(response.body());
                 Log.e("Retrofit", "working :(");

             }

            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {

                Log.e("Retrofit", "Something went wrong adding Animals :(");

            }
        });

        return addAnimalMutableLiveData;

    }




    public LiveData <List<Animal>> getAllAnimalsByUserId(String userId,String eui) {
        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Animal>> call = terrariumApi.getAnimalByUserId(userId,eui);

        call.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {

                if (response.isSuccessful()){
                    getAllAnimalsByUserIdMutableLiveData.setValue(response.body());
                    Log.e("Retrofit", "its working Animals :(" + getAllAnimalsByUserIdMutableLiveData.getValue().size());



                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting Animals :(");

            }
        });
        return getAllAnimalsByUserIdMutableLiveData;
    }



    public LiveData<Animal> updateAnimals(int id, Animal animal){

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Animal> call = terrariumApi.updateAnimal(id,animal);

        call.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                if (response.isSuccessful()){
                  updateAnimals.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong updating Animals :(");
            }
        });

        return updateAnimals;


    }




}
