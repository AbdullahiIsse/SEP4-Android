package com.abdu.and_sep4.View.UpdateTerrarium;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Terrarium;

public interface IUpdateTerrariumFragment {

    LiveData<Terrarium> updateTerrarium(String eui, Terrarium terrarium);
}
