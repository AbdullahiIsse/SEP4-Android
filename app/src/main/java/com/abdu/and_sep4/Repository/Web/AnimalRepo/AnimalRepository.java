package com.abdu.and_sep4.Repository.Web.AnimalRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

public interface AnimalRepository {

    MutableLiveData<Animal> getAddAnimalMutableLiveData();

    LiveData<List<Animal>> getGetAllAnimalsByUserIdMutableLiveData();

    LiveData<Animal> addAnimals(Animal animal);

    LiveData <List<Animal>> getAllAnimalsByUserId(String userId,String eui);

    LiveData<Animal> updateAnimals(int id, Animal animal);
}
