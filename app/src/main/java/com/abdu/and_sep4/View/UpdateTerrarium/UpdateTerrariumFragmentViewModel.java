package com.abdu.and_sep4.View.UpdateTerrarium;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Repository.Web.TerrariumRepo.TerrariumRepository;
import com.abdu.and_sep4.Repository.Web.TerrariumRepo.TerrariumRepositoryImpl;
import com.abdu.and_sep4.Shared.Terrarium;

public class UpdateTerrariumFragmentViewModel extends AndroidViewModel implements IUpdateTerrariumFragment {


    private final TerrariumRepository terrariumRepository;

    public UpdateTerrariumFragmentViewModel(Application application) {
        super(application);

        terrariumRepository = TerrariumRepositoryImpl.getInstance(application);
    }


    public LiveData<Terrarium> updateTerrarium(String eui, Terrarium terrarium){
        return terrariumRepository.updateTerrarium(eui,terrarium);
    }




}
