package com.abdu.and_sep4.View.Account;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.AnimalRepository;
import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

public class AnimalListFragmentViewmodel extends AndroidViewModel {

    private AnimalRepository animalRepository;



    public AnimalListFragmentViewmodel(Application application) {
        super(application);

        animalRepository = AnimalRepository.getInstance(application);

    }


    public LiveData<List<Animal>> getPetsLiveData(String userId,String eui) {

        return animalRepository.getAllAnimalsByUserId(userId,eui);
    }









}
