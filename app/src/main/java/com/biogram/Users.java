package com.biogram;

public class Users {
    public String id;
    public String name;
    // public String imageurl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Users(){
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}