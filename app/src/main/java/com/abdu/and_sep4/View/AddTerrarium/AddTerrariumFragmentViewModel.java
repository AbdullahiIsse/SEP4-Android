package com.abdu.and_sep4.View.AddTerrarium;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.Shared.TerrariumV2;

import java.util.List;

public class AddTerrariumFragmentViewModel extends ViewModel {

    private TerrariumRepository terrariumRepository;
    private final MutableLiveData<Boolean> loading;


    public AddTerrariumFragmentViewModel() {

        terrariumRepository = TerrariumRepository.getInstance();
        loading = new MutableLiveData<>(false);
    }



    public MutableLiveData<TerrariumV2> addTerrariumToDb(TerrariumV2 terrarium){
        return  terrariumRepository.addTerrarium(() -> loading.setValue(false),terrarium);
    }


    public LiveData<Boolean> loading() {
        return loading;
    }
}
