package com.abdu.and_sep4.Shared;

public class Animal {

    private int id;
    private String eui;
    private String name;
    private int age;
    private String species;
    private char gender;
    private boolean shedding;
    private boolean hibernating;
    private boolean hasOffSpring;

    public Animal(int id, String eui, String name, int age, String species, char gender, boolean shedding, boolean hibernating, boolean hasOffSpring) {
        this.id = id;
        this.eui = eui;
        this.name = name;
        this.age = age;
        this.species = species;
        this.gender = gender;
        this.shedding = shedding;
        this.hibernating = hibernating;
        this.hasOffSpring = hasOffSpring;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
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
                ", gender=" + gender +
                ", shedding=" + shedding +
                ", hibernating=" + hibernating +
                ", hasOffSpring=" + hasOffSpring +
                '}';
    }
}
