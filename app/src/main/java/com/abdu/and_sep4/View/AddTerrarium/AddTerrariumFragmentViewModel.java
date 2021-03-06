package com.abdu.and_sep4.View.AddTerrarium;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Web.TerrariumRepo.TerrariumRepository;
import com.abdu.and_sep4.Repository.Web.TerrariumRepo.TerrariumRepositoryImpl;
import com.abdu.and_sep4.Shared.Terrarium;

public class AddTerrariumFragmentViewModel extends AndroidViewModel implements IAddTerrariumFragment{

    private TerrariumRepository terrariumRepository;



    public AddTerrariumFragmentViewModel(Application application) {
        super(application);


        terrariumRepository = TerrariumRepositoryImpl.getInstance(application);

    }



    public LiveData<Terrarium> addTerrariumToDb(Terrarium terrarium){
        return  terrariumRepository.addTerrarium(terrarium);
    }


}
