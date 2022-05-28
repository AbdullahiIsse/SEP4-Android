package com.abdu.and_sep4.View.Adapter;

import com.abdu.and_sep4.Shared.Co2Measurement;
import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.robinhood.spark.SparkAdapter;

import java.util.List;

public class Co2SparkAdapter extends SparkAdapter {


    private List<Co2Measurement> dailyData;


    public Co2SparkAdapter(List<Co2Measurement> dailyData) {
        this.dailyData = dailyData;
    }

    @Override
    public int getCount() {
        return dailyData.size();
    }

    @Override
    public Object getItem(int index) {
        return dailyData.get(index);
    }

    @Override
    public float getY(int index) {

        Co2Measurement dayData = dailyData.get(index);

        return (float) dayData.getMeasurement();


    }







}
