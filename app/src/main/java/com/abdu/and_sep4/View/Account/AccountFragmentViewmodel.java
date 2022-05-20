package com.abdu.and_sep4.View.Account;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.PetRepository;
import com.abdu.and_sep4.Shared.Pet;

import java.util.List;

public class AccountFragmentViewmodel extends ViewModel {


    private PetRepository petRepository;
    private LiveData<List<Pet>> PetResponseLiveData;

    public AccountFragmentViewmodel() {

        petRepository = PetRepository.getInstance();
        PetResponseLiveData = petRepository.getAllPets();
    }


    public LiveData<List<Pet>> getPetsLiveData() {
        return petRepository.getPets();
    }





}
