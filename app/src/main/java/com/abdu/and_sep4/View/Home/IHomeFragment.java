package com.abdu.and_sep4.View.Home;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Terrarium;

import java.util.List;

public interface IHomeFragment {


    LiveData<List<Terrarium>> getTerrariumLiveData(String userId);
}
