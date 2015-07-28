package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */
public class Reader {
    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    private String mName;
    private String mGender;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    private int mId;
}
