package com.abdu.and_sep4.View.UpdateTerrarium;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Terrarium;

public class UpdateTerrariumFragmentViewModel extends ViewModel {


    private TerrariumRepository terrariumRepository;

    public UpdateTerrariumFragmentViewModel() {

        terrariumRepository = TerrariumRepository.getInstance();
    }


    public LiveData<Terrarium> updateTerrarium(String eui, Terrarium terrarium){
        return terrariumRepository.updateTerrarium(eui,terrarium);
    }




}
