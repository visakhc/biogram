package com.biogram;

public class Users {
    public String name;
    public String imageurl;
    public Users(){
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String image) {
        this.imageurl = image;
    }

    public Users(String name, String summary, String imageurl) {
        this.name = name;
        this.imageurl = imageurl;
    }
}