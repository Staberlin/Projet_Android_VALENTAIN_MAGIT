package com.example.user.livesoccer;

public class Item {
    private String mImageUrl;
    private String mCountry;

    public Item(String imageUrl, String country){
        mImageUrl = imageUrl;
        mCountry = country;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCountry() {
        return mCountry;
    }

}
