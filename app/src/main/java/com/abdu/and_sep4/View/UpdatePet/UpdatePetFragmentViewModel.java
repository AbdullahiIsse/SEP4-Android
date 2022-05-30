package com.abdu.and_sep4.View.UpdatePet;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.AnimalRepository;
import com.abdu.and_sep4.Shared.Animal;

public class UpdatePetFragmentViewModel extends ViewModel {

    private AnimalRepository animalRepository;


    public UpdatePetFragmentViewModel() {
        animalRepository = AnimalRepository.getInstance();
    }


    public LiveData<Animal>updatePet(int id, Animal animal){

        return animalRepository.updateAnimals(id,animal);
    }




}
