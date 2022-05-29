package com.abdu.and_sep4.View.AddPet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.PetRepository;
import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Animal;
import com.abdu.and_sep4.Shared.Pet;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.Shared.TerrariumV2;

import java.util.List;

public class AddPetFragmentViewModel extends ViewModel {


    private PetRepository petRepository;
    private LiveData<Pet> petLiveData;
    private TerrariumRepository terrariumRepository;
    private final MutableLiveData<Boolean> loading;

    public AddPetFragmentViewModel() {
        petRepository = PetRepository.getInstance();
        terrariumRepository = TerrariumRepository.getInstance();

        petLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>(false);

    }

    public LiveData<Pet>addPet(Pet pet){

        return petRepository.addPet(pet);

    }

    public MutableLiveData<Animal> addAnimal(Animal animal){
        return  terrariumRepository.addAnimal(() -> loading.setValue(false),animal);
    }


    public LiveData<Boolean> loading() {
        return loading;
    }



}
