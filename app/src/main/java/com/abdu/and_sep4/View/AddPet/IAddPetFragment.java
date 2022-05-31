package com.abdu.and_sep4.View.AddPet;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Animal;

public interface IAddPetFragment {

    LiveData<Animal> addAnimal(Animal animal);
}
