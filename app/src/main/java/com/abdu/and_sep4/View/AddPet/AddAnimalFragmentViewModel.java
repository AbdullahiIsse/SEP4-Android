package com.abdu.and_sep4.View.AddPet;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Web.AnimalRepo.AnimalRepository;
import com.abdu.and_sep4.Repository.Web.AnimalRepo.AnimalRepositoryImpl;
import com.abdu.and_sep4.Shared.Animal;

public class AddAnimalFragmentViewModel extends AndroidViewModel implements IAddPetFragment {


    private AnimalRepository animalRepository;


    public AddAnimalFragmentViewModel(Application application) {
        super(application);
        animalRepository = AnimalRepositoryImpl.getInstance(application);


    }


    public LiveData<Animal> addAnimal(Animal animal){
        return animalRepository.addAnimals(animal);
    }





}
