package com.example.lxia.readingbuddies;

import java.util.Date;
import java.util.UUID;

/**
 * Created by lxia on 7/27/2015.
 */
public class Comment {
    private int mId;
    private String mBookTitle;
    private Date mDate;
    private String mCommentName;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    private String mDescription;
    private String mLocation;

    public String getCommentName() {
        return mCommentName;
    }

    public void setCommentName(String mCommentName) {
        this.mCommentName = mCommentName;
    }

    public Comment() {
        mDate = new Date();
    }

    @Override
    public String toString() {
        return mBookTitle+"\n"+mCommentName;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public void setTitle(String title) {
        mBookTitle = title;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
