package com.abdu.and_sep4.Shared;

public class FoodDispenser {

    private long id;
    private int food;
    private long terrariumId;



    public FoodDispenser(long id, long terrariumId) {
        this.id = id;
        this.terrariumId = terrariumId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public long getTerrariumId() {
        return terrariumId;
    }

    public void setTerrariumId(long terrariumId) {
        this.terrariumId = terrariumId;
    }
}
