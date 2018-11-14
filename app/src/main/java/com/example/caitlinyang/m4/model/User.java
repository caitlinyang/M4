package com.example.caitlinyang.m4.model;

import java.io.Serializable;

/**
 * User Class
 */
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

    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * setter for name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for email
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * setter for email
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for password
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * setter for password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter for user type
     * @return user type
     */
    public String getUserType() {
        return this.userType;
    }

    /**
     * setter for user type
     * @param userType user type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * getter for id
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * constructor for user
     * @param name string
     * @param email string
     * @param password string
     * @param userType string
     */
    public User(String name, String email, String password, String userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.id = User.Next_Id + 1;
        Next_Id++;
    }


}