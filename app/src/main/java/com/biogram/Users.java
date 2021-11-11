package com.biogram;

public class Users {
    public String id;
   // public String imageurl;
    public Users(){
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    //public String getImageurl() {
      //  return imageurl;
    //}

  //  public void setImageurl(String image) {
        //this.imageurl = image;
 //   }

    public Users(String id) {
        this.id = id;
      //  this.imageurl = imageurl;
    }
}