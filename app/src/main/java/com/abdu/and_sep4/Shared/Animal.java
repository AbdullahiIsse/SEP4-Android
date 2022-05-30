package com.abdu.and_sep4.Shared;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "animal_table")
public class Animal {

    @PrimaryKey
    private int id;
    private String eui;
    private String name;
    private int age;
    private String species;
    private String gender;
    private boolean hasOffSpring;
    private boolean hibernating;
    private boolean shedding;

    @Ignore
    public Animal(String eui, String name, int age, String species, String gender, boolean hasOffSpring, boolean hibernating, boolean shedding) {
        this.eui = eui;
        this.name = name;
        this.age = age;
        this.species = species;
        this.gender = gender;
        this.hasOffSpring = hasOffSpring;
        this.hibernating = hibernating;
        this.shedding = shedding;
    }

    public Animal(int id, String eui, String name, int age, String species, String gender, boolean hasOffSpring, boolean hibernating, boolean shedding) {
        this.id = id;
        this.eui = eui;
        this.name = name;
        this.age = age;
        this.species = species;
        this.gender = gender;
        this.hasOffSpring = hasOffSpring;
        this.hibernating = hibernating;
        this.shedding = shedding;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isShedding() {
        return shedding;
    }

    public void setShedding(boolean shedding) {
        this.shedding = shedding;
    }

    public boolean isHibernating() {
        return hibernating;
    }

    public void setHibernating(boolean hibernating) {
        this.hibernating = hibernating;
    }

    public boolean isHasOffSpring() {
        return hasOffSpring;
    }

    public void setHasOffSpring(boolean hasOffSpring) {
        this.hasOffSpring = hasOffSpring;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", eui='" + eui + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", species='" + species + '\'' +
                ", gender='" + gender + '\'' +
                ", hasOffSpring=" + hasOffSpring +
                ", hibernating=" + hibernating +
                ", shedding=" + shedding +
                '}';
    }
}
