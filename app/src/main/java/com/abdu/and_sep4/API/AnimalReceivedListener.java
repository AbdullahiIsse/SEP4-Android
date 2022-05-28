package com.abdu.and_sep4.API;

import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.Shared.Animal;
import com.abdu.and_sep4.Shared.TerrariumV2;

import java.util.List;

public interface AnimalReceivedListener {

    void onAnimalReceived(MutableLiveData<List<Animal>> animal);
}
