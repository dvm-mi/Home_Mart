package com.example.homemart.model;

public class Users {
    private String password, phone, username, image, address;

    public Users() {
    }

    public Users(String password, String phone, String username, String image, String address) {
        this.password = password;
        this.phone = phone;
        this.username = username;
        this.image = image;
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}