package com.abdu.and_sep4.View.Account;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.PetRepository;
import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Animal;
import com.abdu.and_sep4.Shared.Pet;
import com.abdu.and_sep4.Shared.TerrariumV2;

import java.util.List;

public class AnimalListFragmentViewmodel extends ViewModel {

    private TerrariumRepository terrariumRepository;
    private PetRepository petRepository;
    private final MutableLiveData<Boolean> loading;


    public AnimalListFragmentViewmodel() {

        petRepository = PetRepository.getInstance();
        terrariumRepository = TerrariumRepository.getInstance();
        loading = new MutableLiveData<>(true);
    }


    public LiveData<List<Pet>> getPetsLiveData(long id) {

        return petRepository.getAllPets(id);
    }

    public void deletingPet(long id){
        petRepository.deletePets(id);
    }

    public MutableLiveData<List<Animal>> getAnimalByEui(String eui){
        return  terrariumRepository.getAnimalFromSignalR(() -> loading.setValue(false),eui);
    }

    public LiveData<Boolean> loading() {
        return loading;
    }





}
