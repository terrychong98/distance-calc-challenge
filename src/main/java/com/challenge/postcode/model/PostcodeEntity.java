package com.challenge.postcode.model;

public class PostcodeEntity {
    private long id;
    private String postcode;
    private double longitude;
    private double latitude;

    public PostcodeEntity(){}

    public PostcodeEntity(long id, String postcode, double longitude, double latitude) {
        this.id = id;
        this.postcode = postcode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
