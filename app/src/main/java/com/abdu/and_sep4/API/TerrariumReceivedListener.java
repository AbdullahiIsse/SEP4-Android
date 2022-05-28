package com.abdu.and_sep4.API;

import androidx.lifecycle.MutableLiveData;

import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.TerrariumV2;

import java.util.List;

public interface TerrariumReceivedListener {
    void onTerrariumReceived(MutableLiveData<List<TerrariumV2>> terrarium);

}