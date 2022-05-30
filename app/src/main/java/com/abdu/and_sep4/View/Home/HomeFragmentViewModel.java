package com.abdu.and_sep4.View.Home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.Repository.Web.TerrariumRepository;
import com.abdu.and_sep4.Repository.WebOrLocale.TerrariumWebOrLocaleRepository;
import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;



public class HomeFragmentViewModel extends AndroidViewModel {

    private TerrariumWebOrLocaleRepository terrariumWebOrLocaleRepository;
    private LiveData<List<Terrarium>> terrariumLiveData;



    public HomeFragmentViewModel(Application application) {
        super(application);

        terrariumWebOrLocaleRepository = TerrariumWebOrLocaleRepository.getInstance(application);

        terrariumLiveData = new MutableLiveData<>();


    }


    public LiveData<List<Terrarium>> getTerrariumLiveData(String userId) {
        return terrariumWebOrLocaleRepository.getTerrariumsByUserId(userId);
    }











}
