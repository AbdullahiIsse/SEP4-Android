package com.abdu.and_sep4.Shared;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaveInfo {

    private User user;
    private Terrarium terrarium;


    private static SaveInfo instance;
    private static Lock lock = new ReentrantLock();

    private SaveInfo() {}

    public static SaveInfo getInstance() {
        // if one doesn't already exist, create it
        if (instance == null) {
            synchronized (lock) {
                if (instance == null)
                    instance = new SaveInfo();
            }
        }
        return instance;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Terrarium getTerrarium() {
        return terrarium;
    }

    public void setTerrarium(Terrarium terrarium) {
        this.terrarium = terrarium;
    }

    @Override
    public String toString() {
        return "SaveInfo{" +
                "user=" + user +
                ", terrarium=" + terrarium +
                '}';
    }











}
