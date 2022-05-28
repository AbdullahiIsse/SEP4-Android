package com.abdu.and_sep4.Shared;

public class MeasurementV2 {


    private int id;
    private String eui;


    public MeasurementV2() {
    }

    public MeasurementV2(String eui) {
        this.eui = eui;

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


}
