package com.example.covid19helper;

public class CountrywiseModel {
    private String country;
    private long mild, death,critical,recovered;


    private CountrywiseModel(){}
    private CountrywiseModel(String country,long mild,long death,long critical,long recovered){
        this.country=country;
        this.mild=mild;
        this.critical=critical;
        this.death=death;
        this.recovered=recovered;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getMild() {
        return mild;
    }

    public void setMild(long mild) {
        this.mild = mild;
    }

    public long getDeath() {
        return death;
    }

    public void setDeath(long death) {
        this.death = death;
    }

    public long getCritical() {
        return critical;
    }

    public void setCritical(long critical) {
        this.critical = critical;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }
}
