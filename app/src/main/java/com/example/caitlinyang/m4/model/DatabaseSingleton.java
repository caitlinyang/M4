package com.example.caitlinyang.m4.model;

public class DatabaseSingleton {
    private static final DatabaseSingleton ourInstance = new DatabaseSingleton();

    public static DatabaseSingleton getInstance() {
        return ourInstance;
    }

    private DatabaseSingleton() {
        db = new DataBase();
    }

    private DataBase db;

    public DataBase getDb() {
        return db;
    }

    public void setDb(DataBase db) {
        this.db = db;
    }
}
