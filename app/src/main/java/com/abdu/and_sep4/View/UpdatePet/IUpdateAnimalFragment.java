package com.abdu.and_sep4.View.UpdatePet;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Animal;

public interface IUpdateAnimalFragment {

    LiveData<Animal> updatePet(int id, Animal animal);
}
