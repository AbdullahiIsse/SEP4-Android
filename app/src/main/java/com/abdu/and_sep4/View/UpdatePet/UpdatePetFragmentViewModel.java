package com.abdu.and_sep4.View.UpdatePet;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.PetRepository;
import com.abdu.and_sep4.Shared.Pet;

public class UpdatePetFragmentViewModel extends ViewModel {

    private PetRepository petRepository;


    public UpdatePetFragmentViewModel() {
        petRepository = PetRepository.getInstance();
    }


    public LiveData<Pet>updatePet(long id,Pet pet){
        return petRepository.updatePet(id, pet);
    }




}
