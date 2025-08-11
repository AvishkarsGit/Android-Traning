package com.avishkar.recyclerviewexample.models;

public class UserModel {

    private int rollNo;
    private String name;

    public UserModel(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    //getter and setter

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
