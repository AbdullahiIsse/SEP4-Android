package com.abdu.and_sep4.View.AddTerrarium;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Terrarium;

public class AddTerrariumFragmentViewModel extends ViewModel {

    private TerrariumRepository terrariumRepository;



    public AddTerrariumFragmentViewModel() {


        terrariumRepository = TerrariumRepository.getInstance();

    }



    public LiveData<Terrarium> addTerrariumToDb(Terrarium terrarium){
        return  terrariumRepository.addTerrarium(terrarium);
    }


}
