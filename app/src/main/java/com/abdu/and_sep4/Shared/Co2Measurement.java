package com.abdu.and_sep4.Shared;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class Co2Measurement {


    private int id;
    private String eui;
    private String timestamp;
    private int measurement;

    public Co2Measurement(int id, String eui, String timestamp, int measurement) {
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

    public int getMeasurement() {
        return measurement;
    }

    public void setMeasurement(int measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Co2Measurement{" +
                "id=" + id +
                ", eui='" + eui + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", measurement=" + measurement +
                '}';
    }
}
