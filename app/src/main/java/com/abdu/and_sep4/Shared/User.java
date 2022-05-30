package com.abdu.and_sep4.Shared;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class User {

    private String id;

    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





}
