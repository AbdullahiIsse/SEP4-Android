package com.abdu.and_sep4.Repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.Pet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetRepository {

    private static PetRepository instance;

    private final MutableLiveData <Pet> petRepositoryMutableLiveData;

    private final MutableLiveData<List<Pet>> pets;


    public PetRepository() {
        petRepositoryMutableLiveData = new MutableLiveData<>();
        pets = new MutableLiveData<>();

    }


    public static synchronized PetRepository getInstance() {

        if (instance == null){
            instance = new PetRepository();
        }

        return instance;
    }


    public MutableLiveData<Pet> getPetRepositoryMutableLiveData() {
        return petRepositoryMutableLiveData;
    }

    public LiveData<List<Pet>> getPets() {
        return pets;
    }


    public LiveData<Pet> addPet(Pet pet) {

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Pet> call = terrariumApi.addPet(pet);


        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
             if(response.isSuccessful()) {
                 petRepositoryMutableLiveData.setValue(response.body());
                 Log.e("Retrofit", "working :(");

             }

            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {

                Log.e("Retrofit", "Something went wrong adding pet :(");

            }
        });

        return petRepositoryMutableLiveData;

    }




    public LiveData <List<Pet>> getAllPets(long id) {
        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Pet>> call = terrariumApi.getPets(id);

        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {

                if (response.isSuccessful()){
                    pets.setValue(response.body());
                    Log.e("Retrofit", "its working pet :(" + pets.getValue().size());



                }
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting pets :(");

            }
        });
        return pets;
    }


    public void deletePets(long id){
        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Pet> call = terrariumApi.deletePet(id);

        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                if (response.isSuccessful()){
                    Log.e("Retrofit", "deleting pet successfully");
                }
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong deleting pets :(");
            }
        });

    }


}
