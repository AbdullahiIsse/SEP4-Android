package com.abdu.and_sep4.View.Account;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.AnimalRepository;
import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

public class AnimalListFragmentViewmodel extends ViewModel {

    private AnimalRepository animalRepository;



    public AnimalListFragmentViewmodel() {

        animalRepository = AnimalRepository.getInstance();

    }


    public LiveData<List<Animal>> getPetsLiveData(String userId,String eui) {

        return animalRepository.getAllAnimalsByUserId(userId,eui);
    }









}
