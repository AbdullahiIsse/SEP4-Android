package com.abdu.and_sep4.Repository.WebOrLocale.AnimalRepo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

public interface AnimalWebOrLocaleRepository {

    boolean ifNetworkIsAvailable();

    LiveData<List<Animal>> getAllAnimalsByUserId(String userId, String eui);
}
