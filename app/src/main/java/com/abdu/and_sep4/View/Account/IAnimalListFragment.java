package com.abdu.and_sep4.View.Account;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Animal;

import java.util.List;

public interface IAnimalListFragment {

    LiveData<List<Animal>> getPetsLiveData(String userId, String eui);
}
