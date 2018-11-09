package com.example.caitlinyang.m4.model;

import java.io.Serializable;

public class User implements Serializable{
    /**
     * allow us to assign unique id numbers to each student
     */
    @SuppressWarnings("CanBeFinal")
    private static int Next_Id = 0;

    private final int id;

    private String name;
    private String email;
    private String password;
    private String userType;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getId() {
        return this.id;
    }


    public User(String name, String email, String password, String userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.id = User.Next_Id + 1;
    }


}