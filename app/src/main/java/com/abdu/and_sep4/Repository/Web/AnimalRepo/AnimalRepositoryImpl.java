package com.abdu.and_sep4.Repository.Web.AnimalRepo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Dao.AnimalDao;
import com.abdu.and_sep4.Dao.TerrariumDatabase;
import com.abdu.and_sep4.Shared.Animal;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalRepositoryImpl implements AnimalRepository {

    private static AnimalRepositoryImpl instance;
    private final AnimalDao animalDao;

    private final MutableLiveData <Animal> addAnimalMutableLiveData;

    private final MutableLiveData<List<Animal>> getAllAnimalsByUserIdMutableLiveData;

    private final MutableLiveData<Animal> updateAnimals;

    private final ExecutorService executorService;


    private AnimalRepositoryImpl(Application application) {
        TerrariumDatabase terrariumDatabase = TerrariumDatabase.getInstance(application);
        animalDao = terrariumDatabase.animalDao();
        addAnimalMutableLiveData = new MutableLiveData<>();
        getAllAnimalsByUserIdMutableLiveData = new MutableLiveData<>();
        updateAnimals = new MutableLiveData<>();
        executorService = Executors.newFixedThreadPool(2);

    }


    public static synchronized AnimalRepositoryImpl getInstance(Application application) {

        if (instance == null){
            instance = new AnimalRepositoryImpl(application);
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
                    for (Animal animal : response.body()) {
                        executorService.execute(() -> animalDao.addAnimal(animal));
                    }
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
