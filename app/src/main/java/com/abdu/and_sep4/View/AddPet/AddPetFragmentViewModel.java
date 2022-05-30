package com.abdu.and_sep4.View.AddPet;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Web.AnimalRepository;
import com.abdu.and_sep4.Shared.Animal;

public class AddPetFragmentViewModel extends AndroidViewModel {


    private AnimalRepository animalRepository;


    public AddPetFragmentViewModel(Application application) {
        super(application);
        animalRepository = AnimalRepository.getInstance(application);


    }


    public LiveData<Animal> addAnimal(Animal animal){
        return animalRepository.addAnimals(animal);
    }





}
