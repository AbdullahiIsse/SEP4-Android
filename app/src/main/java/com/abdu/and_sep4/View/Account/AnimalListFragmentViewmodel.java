package com.abdu.and_sep4.View.Account;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.PetRepository;
import com.abdu.and_sep4.Shared.Pet;

import java.util.List;

public class AnimalListFragmentViewmodel extends ViewModel {


    private PetRepository petRepository;


    public AnimalListFragmentViewmodel() {

        petRepository = PetRepository.getInstance();

    }


    public LiveData<List<Pet>> getPetsLiveData(long id) {
        return petRepository.getAllPets(id);
    }





}
