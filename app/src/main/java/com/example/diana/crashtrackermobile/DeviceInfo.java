package com.example.diana.crashtrackermobile;

import java.util.Date;

public class DeviceInfo {
    public String id;
    public String name;
    public String type;
    public String model;
    public String description;
    public int serviceInterval;
    public Date dateLastService;
    public float notifyBeforeService;
    public int status;
    public String userId;
    public Date createdAt;
    public Date updatedAt;

    public DeviceInfo(String id, String name, String type, String model, String description, int serviceInterval, Date dateLastService, float notifyBeforeService, int status, String userId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.model = model;
        this.description = description;
        this.serviceInterval = serviceInterval;
        this.dateLastService = dateLastService;
        this.notifyBeforeService = notifyBeforeService;
        this.status = status;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
