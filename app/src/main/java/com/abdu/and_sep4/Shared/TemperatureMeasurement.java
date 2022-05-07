package com.abdu.and_sep4.Shared;

public class TemperatureMeasurement {

    private int id;
    private int temperatureReading;


    public TemperatureMeasurement(int id, int temperatureReading) {
        this.id = id;
        this.temperatureReading = temperatureReading;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemperatureReading() {
        return temperatureReading;
    }

    public void setTemperatureReading(int temperatureReading) {
        this.temperatureReading = temperatureReading;
    }
}
