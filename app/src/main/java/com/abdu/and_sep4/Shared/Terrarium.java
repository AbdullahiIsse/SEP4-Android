package com.abdu.and_sep4.Shared;

public class Terrarium {


    private int id;
    private String terrariumName;
    private String userId;

    public Terrarium(String terrariumName, String userId) {
        this.terrariumName = terrariumName;
        this.userId = userId;
    }

    public Terrarium(int id, String terrariumName) {
        this.id = id;
        this.terrariumName = terrariumName;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
