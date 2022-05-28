package com.abdu.and_sep4.View.Adapter;

import com.abdu.and_sep4.Shared.HumidityMeasurement;
import com.abdu.and_sep4.Shared.MeasurementsType;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.robinhood.spark.SparkAdapter;

import java.util.List;

public class HumiditySparkAdapter extends SparkAdapter {
    private List<HumidityMeasurement> dailyData;


    public HumiditySparkAdapter(List<HumidityMeasurement> dailyData) {
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

        HumidityMeasurement dayData = dailyData.get(index);

        return (float) dayData.getMeasurement();


    }
}
