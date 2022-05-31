package com.abdu.and_sep4.Repository.Locale.TerrariumRepo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

public interface TerrariumRepositoryOffline {

    LiveData<List<Terrarium>> getTerrariumsByUserId(String userId);
}
