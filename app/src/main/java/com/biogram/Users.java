package com.biogram;

public class Users {
   public String namee, status;

    public String getNamee() {
        return namee;
    }

    public void setNamee(String namee) {
        this.namee = namee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users() {
    }

    public Users(String namee, String status) {
        this.namee = namee;
        this.status = status;
    }
}
