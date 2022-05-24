package com.abdu.and_sep4.View.UpdateTerrarium;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Pet;
import com.abdu.and_sep4.Shared.Terrarium;

public class UpdateTerrariumFragmentViewModel extends ViewModel {


    private TerrariumRepository terrariumRepository;

    public UpdateTerrariumFragmentViewModel() {
        terrariumRepository = TerrariumRepository.getInstance();
    }


    public LiveData<Terrarium> updateTerrarium(long id, Terrarium terrarium){
        return terrariumRepository.updateTerrarium(id,terrarium);
    }




}