package com.abdu.and_sep4.View.AddTerrarium;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Terrarium;

public interface IAddTerrariumFragment {

    LiveData<Terrarium> addTerrariumToDb(Terrarium terrarium);
}
