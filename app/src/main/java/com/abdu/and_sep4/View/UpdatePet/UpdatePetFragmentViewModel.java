package com.abdu.and_sep4.View.UpdatePet;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.AnimalRepository;
import com.abdu.and_sep4.Shared.Animal;

public class UpdatePetFragmentViewModel extends AndroidViewModel {

    private AnimalRepository animalRepository;


    public UpdatePetFragmentViewModel(Application application) {
        super(application);
        animalRepository = AnimalRepository.getInstance(application);
    }


    public LiveData<Animal>updatePet(int id, Animal animal){

        return animalRepository.updateAnimals(id,animal);
    }




}
