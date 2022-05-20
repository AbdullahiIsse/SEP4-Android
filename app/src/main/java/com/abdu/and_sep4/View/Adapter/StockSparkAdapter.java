package com.abdu.and_sep4.View.Adapter;

import com.abdu.and_sep4.Shared.Measurements;
import com.abdu.and_sep4.Shared.MeasurementsType;
import com.abdu.and_sep4.Shared.Temperatur;
import com.abdu.and_sep4.Shared.TemperatureMeasurement;
import com.abdu.and_sep4.Shared.Terrarium;
import com.robinhood.spark.SparkAdapter;

import java.util.List;

public class StockSparkAdapter extends SparkAdapter {

    private List<Measurements> dailyData;
    public MeasurementsType measurementsType = MeasurementsType.TEMPERATURE;


    public StockSparkAdapter(List<Measurements> dailyData) {
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

        Measurements dayData = dailyData.get(index);

        if (measurementsType == MeasurementsType.CO2) {
            return (float) dayData.getMeasurement_co2();
        } else if (measurementsType == MeasurementsType.TEMPERATURE) {
            return (float) dayData.getMeasurement_temp();
        } else if (measurementsType == MeasurementsType.AIRHUMIDITY) {
            return (float) dayData.getMeasurement_air();
        }

       return 0;
    }
}
