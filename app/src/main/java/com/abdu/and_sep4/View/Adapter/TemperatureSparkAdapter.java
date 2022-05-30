package com.abdu.and_sep4.View.Adapter;

import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.robinhood.spark.SparkAdapter;

import java.util.List;

public class TemperatureSparkAdapter extends SparkAdapter {

    private List<TemperatureMeasurement> dailyData;



    public TemperatureSparkAdapter(List<TemperatureMeasurement> dailyData) {
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

        TemperatureMeasurement dayData = dailyData.get(index);

        return (float) dayData.getMeasurement();


    }
}
