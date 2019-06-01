package com.example.diana.crashtrackermobile;

import java.util.Date;

public class User {
    public String id;
    public String email;
    public String firstName;
    public String lastName;
    public String password;
    public Date createdAt;
    public Date updatedAt;

    public User(String id, String email, String firstName, String lastName, String password, Date createdAt, Date updatedAt) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
