package com.abdu.and_sep4.API;

import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

public interface AnimalAddListener {

    void AnimalAddListener(MutableLiveData<Animal> addAnimal);
}
