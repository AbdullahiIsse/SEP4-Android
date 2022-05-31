package com.abdu.and_sep4.View.UpdatePet;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Web.AnimalRepo.AnimalRepository;
import com.abdu.and_sep4.Repository.Web.AnimalRepo.AnimalRepositoryImpl;
import com.abdu.and_sep4.Shared.Animal;

public class UpdateAnimalFragmentViewModel extends AndroidViewModel implements IUpdateAnimalFragment {

    private AnimalRepository animalRepository;


    public UpdateAnimalFragmentViewModel(Application application) {
        super(application);
        animalRepository = AnimalRepositoryImpl.getInstance(application);
    }


    public LiveData<Animal>updatePet(int id, Animal animal){

        return animalRepository.updateAnimals(id,animal);
    }




}
