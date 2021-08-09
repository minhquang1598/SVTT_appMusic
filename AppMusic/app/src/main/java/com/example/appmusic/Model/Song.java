package com.example.appmusic.Model;

public class Song {
    private String mName;
    private String mTime;
    private String mPath;
    private int mId;

    public Song(String mName, String mTime, String mPath, int mId) {
        this.mName = mName;
        this.mTime = mTime;
        this.mPath = mPath;
        this.mId = mId;
    }

    public Song() {

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
