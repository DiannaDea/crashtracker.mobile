package com.example.diana.crashtrackermobile;

import java.util.Date;

public class Sector {
    public Date createdAt;
    public Date updatedAt;
    public String id;
    public String location;
    public float maxTemperature;
    public int maxTimeExcess;
    public float minTemperature;
    public String name;
    public Date nextServiceDate;
    public int number;
    public SectorStatus status;
    public Date trackerSetupDate;
    public String deviceId;
    public String uuid;

    public Sector(Date createdAt, Date updatedAt, String id, String location, float maxTemperature, int maxTimeExcess, float minTemperature, String name, Date nextServiceDate, int number, SectorStatus status, Date trackerSetupDate, String deviceId, String uuid){
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.location = location;
        this.maxTemperature = maxTemperature;
        this.maxTimeExcess = maxTimeExcess;
        this.minTemperature = minTemperature;
        this.name = name;
        this.nextServiceDate = nextServiceDate;
        this.number = number;
        this.status = status;
        this.trackerSetupDate = trackerSetupDate;
        this.deviceId = deviceId;
        this.uuid = uuid;
    }
}
