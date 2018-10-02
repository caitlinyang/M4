package com.example.caitlinyang.m4.model;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private List<User> userList;


    public List getUserList() {
        return userList;
    }

    public DataBase() {
        userList = new ArrayList<>();
    }

    public boolean addStudent(User user) {

        //go through each user looking for duplicate name   O(n)
        for (User u : userList) {
            if (u.getEmail().equals(user.getEmail())) {
                //oops found duplicate email, don't add and return failure signal
                return false;
            }
        }
        //never found the name so safe to add it.
        userList.add(user);

        //return the success signal
        return true;
    }
}
