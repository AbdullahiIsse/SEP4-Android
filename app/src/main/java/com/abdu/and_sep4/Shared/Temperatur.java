package com.abdu.and_sep4.Shared;

import java.time.LocalDateTime;
import java.util.Date;

public class Temperatur {

     private long id;
     private double temperatureCelsius;
     private String temperatureDate;
     private long terrariumId;

    public Temperatur(long id, double temperatureCelsius, String temperatureDate, long terrariumId) {
        this.id = id;
        this.temperatureCelsius = temperatureCelsius;
        this.temperatureDate = temperatureDate;
        this.terrariumId = terrariumId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public void setTemperatureCelsius(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    public String getTemperatureDate() {
        return temperatureDate;
    }

    public void setTemperatureDate(String temperatureDate) {
        this.temperatureDate = temperatureDate;
    }

    public long getTerrariumId() {
        return terrariumId;
    }

    public void setTerrariumId(long terrariumId) {
        this.terrariumId = terrariumId;
    }
}
