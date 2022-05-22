package com.abdu.and_sep4.Repository;

import android.util.Log;

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

    private final MutableLiveData <Pet> addPetMutableLiveData;

    private final MutableLiveData<List<Pet>> getAllPetMutableLiveData;

    private final MutableLiveData<Pet> updatePet;


    public PetRepository() {
        addPetMutableLiveData = new MutableLiveData<>();
        getAllPetMutableLiveData = new MutableLiveData<>();
        updatePet = new MutableLiveData<>();

    }


    public static synchronized PetRepository getInstance() {

        if (instance == null){
            instance = new PetRepository();
        }

        return instance;
    }


    public MutableLiveData<Pet> getAddPetMutableLiveData() {
        return addPetMutableLiveData;
    }

    public LiveData<List<Pet>> getGetAllPetMutableLiveData() {
        return getAllPetMutableLiveData;
    }


    public LiveData<Pet> addPet(Pet pet) {

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Pet> call = terrariumApi.addPet(pet);


        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
             if(response.isSuccessful()) {
                 addPetMutableLiveData.setValue(response.body());
                 Log.e("Retrofit", "working :(");

             }

            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {

                Log.e("Retrofit", "Something went wrong adding pet :(");

            }
        });

        return addPetMutableLiveData;

    }




    public LiveData <List<Pet>> getAllPets(long id) {
        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<List<Pet>> call = terrariumApi.getPets(id);

        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {

                if (response.isSuccessful()){
                    getAllPetMutableLiveData.setValue(response.body());
                    Log.e("Retrofit", "its working pet :(" + getAllPetMutableLiveData.getValue().size());



                }
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong getting pets :(");

            }
        });
        return getAllPetMutableLiveData;
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

    public LiveData<Pet> updatePet(long id,Pet pet){

        TerrariumApi terrariumApi = ServiceGenerator.getTerrariumApi();
        Call<Pet> call = terrariumApi.updatePet(id,pet);

        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                if (response.isSuccessful()){
                  updatePet.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                Log.e("Retrofit", "Something went wrong updating pets :(");
            }
        });

        return updatePet;


    }




}
