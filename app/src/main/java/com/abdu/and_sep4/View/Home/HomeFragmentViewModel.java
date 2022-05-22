package com.abdu.and_sep4.View.Home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;



public class HomeFragmentViewModel extends ViewModel {

    private TerrariumRepository terrariumRepository;
    private LiveData<List<Terrarium>> terrariumLiveData;


    public HomeFragmentViewModel() {
        terrariumRepository = TerrariumRepository.getInstance();

        terrariumLiveData = new MutableLiveData<>();

    }


    public LiveData<List<Terrarium>> getTerrariumLiveData( int id) {
        return terrariumRepository.getTerrariumByUserId(id);
    }

    public void deleteTerrarium(long id){
        terrariumRepository.deleteTerrarium(id);
    }









}
