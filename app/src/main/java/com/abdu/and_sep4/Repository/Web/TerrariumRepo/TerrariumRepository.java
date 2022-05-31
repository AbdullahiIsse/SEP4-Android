package com.abdu.and_sep4.Repository.Web.TerrariumRepo;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

public interface TerrariumRepository {

    LiveData<List<Terrarium>> getTerrariumByUserId(String userid);

    LiveData<Terrarium> addTerrarium(Terrarium terrarium);

    LiveData<Terrarium> updateTerrarium(String eui, Terrarium terrarium);

    void feedAnimal(String eui);

}
