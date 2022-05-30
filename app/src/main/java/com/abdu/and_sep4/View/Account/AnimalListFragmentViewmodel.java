package com.abdu.and_sep4.View.Account;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Web.AnimalRepository;
import com.abdu.and_sep4.Repository.WebOrLocale.AnimalWebOrLocaleRepository;
import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

public class AnimalListFragmentViewmodel extends AndroidViewModel {

    private AnimalWebOrLocaleRepository animalWebOrLocaleRepository;



    public AnimalListFragmentViewmodel(Application application) {
        super(application);

        animalWebOrLocaleRepository = AnimalWebOrLocaleRepository.getInstance(application);

    }


    public LiveData<List<Animal>> getPetsLiveData(String userId,String eui) {

        return animalWebOrLocaleRepository.getAllAnimalsByUserId(userId,eui);
    }









}
