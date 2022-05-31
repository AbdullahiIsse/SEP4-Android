package com.abdu.and_sep4.Repository.WebOrLocale.TerrariumRepo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

public interface TerrariumWebOrLocaleRepository {

    boolean ifNetworkIsAvailable();

    LiveData<List<Terrarium>> getTerrariumsByUserId(String userid);

}
