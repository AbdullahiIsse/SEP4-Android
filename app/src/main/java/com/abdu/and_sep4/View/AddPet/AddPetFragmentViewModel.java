package com.abdu.and_sep4.View.AddPet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.AnimalRepository;
import com.abdu.and_sep4.Shared.Animal;

public class AddPetFragmentViewModel extends ViewModel {


    private AnimalRepository animalRepository;


    public AddPetFragmentViewModel() {
        animalRepository = AnimalRepository.getInstance();


    }


    public LiveData<Animal> addAnimal(Animal animal){
        return animalRepository.addAnimals(animal);
    }





}
