package com.example.finalproject;

public class Pioneer {
    private String name;
    private String country;
    private String bio;
    private String photoFileName;


    public Pioneer(String name, String country, String bio, String photoFileName) {
        this.name = name;
        this.country = country;
        this.bio = bio;
        this.photoFileName = photoFileName;

    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getBio() {
        return bio;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }


    }


