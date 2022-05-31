package com.abdu.and_sep4.View.CO2;

import androidx.lifecycle.LiveData;

import com.abdu.and_sep4.Shared.Co2Measurement;

import java.util.List;

public interface ICo2Fragment {

    LiveData<List<Co2Measurement>> getCo2(String userId, String eui);
}
