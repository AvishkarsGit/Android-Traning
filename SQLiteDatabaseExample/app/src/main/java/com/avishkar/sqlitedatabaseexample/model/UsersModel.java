package com.avishkar.sqlitedatabaseexample.model;

public class UsersModel {
    int id;
    String name,email,phone,img;

    public UsersModel() {
        //default constructor
    }

    public UsersModel(int id, String name, String email, String phone,String imgUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.img = imgUrl;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
