package com.example.diana.crashtrackermobile;

public class State {
    public String userId;
    public String baseUrl = "https://crash-tracker-server.herokuapp.com/api";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    private static final State state = new State();
    public static State getInstance() {return state;}
}