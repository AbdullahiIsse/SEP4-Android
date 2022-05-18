package com.abdu.and_sep4.View.Adapter;

import com.abdu.and_sep4.Shared.Temperatur;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;
import com.robinhood.spark.SparkAdapter;

import java.util.List;

public class StockSparkAdapter extends SparkAdapter {

    private List<Temperatur> dailyData;


    public StockSparkAdapter(List<Temperatur> dailyData) {
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

        Temperatur dayData = dailyData.get(index);

        return (float) dayData.getTemperatureCelsius();
    }
}
