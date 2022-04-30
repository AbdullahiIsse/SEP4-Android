package com.abdu.and_sep4.Shared;

import java.util.ArrayList;

public class Terrarium {


    private int id;
    private String terrariumName;
    private int userId;

    public Terrarium(int id, String terrariumName, int userId) {
        this.id = id;
        this.terrariumName = terrariumName;
        this.userId = userId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerrariumName() {
        return terrariumName;
    }

    public void setTerrariumName(String terrariumName) {
        this.terrariumName = terrariumName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
