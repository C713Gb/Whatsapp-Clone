package com.example.whatsappclone.Models;

public class User {

    private String id;
    private String username;
    private String imageURI;
    private String phone;
    private String status;

    public User() {
    }

    public User(String id, String username, String imageURI, String phone, String status) {
        this.id = id;
        this.username = username;
        this.imageURI = imageURI;
        this.phone = phone;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
