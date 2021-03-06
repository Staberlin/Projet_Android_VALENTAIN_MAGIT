package com.example.user.livesoccer;

public class Item {
    private String mImageUrl;
    private String mTitle;
    private String mDescription;
    private String mDate;
    private String mURL;

    public Item(String imageUrl, String title, String description, String date, String url){
        mImageUrl = imageUrl;
        mTitle = title;
        mDescription = description;
        mDate = date;
        mURL = url;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDate() {
        return mDate;
    }

    public String getURL() {
        return mURL;
    }
}
