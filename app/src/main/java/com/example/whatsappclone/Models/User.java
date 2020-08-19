package com.example.whatsappclone.Models;

public class User {

    private String id;
    private String username;
    private String imageURI;

    public User() {
    }

    public User(String id, String username, String imageURI) {
        this.id = id;
        this.username = username;
        this.imageURI = imageURI;
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
}
