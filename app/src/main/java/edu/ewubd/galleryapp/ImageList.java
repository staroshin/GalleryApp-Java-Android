package edu.ewubd.galleryapp;

public class ImageList {
    String imageID;
    String description;

    public ImageList(String imageID, String description)
    {
        this.imageID = imageID;
        this.description = description;
    }



    public String getimageID()
    {

        return imageID;
    }



    public String getDescription()
    {

        return description;
    }



}