package com.abdu.and_sep4.Shared;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "humidityMeasurement_table")
public class HumidityMeasurement {
    @PrimaryKey
    private int id;
    private String eui;
    private String timestamp;
    private double measurement;

    public HumidityMeasurement(int id, String eui, String timestamp, double measurement) {
        this.id = id;
        this.eui = eui;
        this.timestamp = timestamp;
        this.measurement = measurement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEui() {
        return eui;
    }

    public void setEui(String eui) {
        this.eui = eui;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getMeasurement() {
        return measurement;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }


    public String toString() {
        return "HumidityMeasurement{" +
                "id=" + id +
                ", eui='" + eui + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", measurement=" + measurement +
                '}';
    }
}
