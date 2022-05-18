package com.abdu.and_sep4.View.AddPet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.PetRepository;
import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Pet;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

public class AddPetFragmentViewModel extends ViewModel {


    private PetRepository petRepository;
    private LiveData<Pet> petLiveData;

    public AddPetFragmentViewModel() {
        petRepository = PetRepository.getInstance();

        petLiveData = new MutableLiveData<>();

    }

    public LiveData<Pet>addPet(Pet pet){

        return petRepository.addPet(pet);

    }



}
