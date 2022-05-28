package com.abdu.and_sep4.View.Home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdu.and_sep4.Repository.TerrariumRepository;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;
import com.abdu.and_sep4.Shared.TerrariumV2;

import java.util.List;



public class HomeFragmentViewModel extends ViewModel {

    private TerrariumRepository terrariumRepository;
    private LiveData<List<Terrarium>> terrariumLiveData;
    private final MutableLiveData<Boolean> loading;


    public HomeFragmentViewModel() {
        terrariumRepository = TerrariumRepository.getInstance();

        terrariumLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>(false);

    }


    public LiveData<List<Terrarium>> getTerrariumLiveData( String id) {
        return terrariumRepository.getTerrariumByUserId(id);
    }

    public void deleteTerrarium(long id){
        terrariumRepository.deleteTerrarium(id);
    }


    public MutableLiveData<List<TerrariumV2>> getTerrariumByUserId(String userId){
        return  terrariumRepository.getTerrariumByUserIdFromSignalR(() -> loading.setValue(false),userId);
    }


    public LiveData<Boolean> loading() {
        return loading;
    }









}
