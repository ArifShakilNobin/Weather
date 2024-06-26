package com.weather.detail;


@lombok.Data
public class FavouriteList {
    public  FavouriteList(String name) {
        this.name = name;
    }
    public FavouriteList() {
    }
    String name;
    LocationInformation locationInformation;
}
