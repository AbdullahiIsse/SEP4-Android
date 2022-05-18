package com.abdu.and_sep4.Repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.API.ServiceGenerator;
import com.abdu.and_sep4.API.TerrariumApi;
import com.abdu.and_sep4.Shared.Pet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetRepository {

    private static PetRepository instance;

    private final MutableLiveData <Pet> petRepositoryMutableLiveData;

    public PetRepository() {
        petRepositoryMutableLiveData = new MutableLiveData<>();
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

}
