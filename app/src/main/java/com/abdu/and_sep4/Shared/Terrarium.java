package com.abdu.and_sep4.Shared;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;


public class Terrarium {


    private String eui;
    private String userId;
    private double minTemperature;
    private double maxTemperature;
    private double minHumidity;
    private double maxHumidity;
    private int maxCarbonDioxide;

    public Terrarium(String eui, String userId, double minTemperature, double maxTemperature, double minHumidity, double maxHumidity, int maxCarbonDioxide) {
        this.eui = eui;
        this.userId = userId;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.maxCarbonDioxide = maxCarbonDioxide;
    }


    public String getEui() {
        return eui;
    }

    public void setEui(String eui) {
        this.eui = eui;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(double minHumidity) {
        this.minHumidity = minHumidity;
    }

    public double getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(double maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public int getMaxCarbonDioxide() {
        return maxCarbonDioxide;
    }

    public void setMaxCarbonDioxide(int maxCarbonDioxide) {
        this.maxCarbonDioxide = maxCarbonDioxide;
    }

    @Override
    public String toString() {
        return "TerrariumV2{" +
                "eui='" + eui + '\'' +
                ", userId='" + userId + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", minHumidity=" + minHumidity +
                ", maxHumidity=" + maxHumidity +
                ", maxCarbonDioxide=" + maxCarbonDioxide +
                '}';
    }
}
