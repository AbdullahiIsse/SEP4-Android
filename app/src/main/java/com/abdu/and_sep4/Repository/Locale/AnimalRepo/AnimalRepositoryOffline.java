package com.abdu.and_sep4.Repository.Locale.AnimalRepo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

public interface AnimalRepositoryOffline {

    LiveData<List<Animal>> getAnimalsByEui(String eui);
}
