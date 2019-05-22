package com.example.diana.crashtrackermobile;

public class SectorStatus {
    public float currentTemp;
    public String name;
    public String number;
    public int timeExcess;

    public SectorStatus(float currentTemp, String name, String number, int timeExcess) {
        this.currentTemp = currentTemp;
        this.name = name;
        this.number = number;
        this.timeExcess = timeExcess;
    }
}
