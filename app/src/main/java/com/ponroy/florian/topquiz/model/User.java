package com.ponroy.florian.topquiz.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

/**
 * Created by Florian PONROY - OpenClassrooms on 09/08/17.
 */

public class User implements Parcelable{

    private String mFirstname;
    private  int mBestScore;

    public  User(){setmBestScore(0);}

    public User(String mFirstname, @Nullable int bestScore) {
        this.mFirstname = mFirstname;
        this.mBestScore = bestScore;
    }

    protected User(Parcel in) {
        mFirstname = in.readString();
        mBestScore = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    public int getmBestScore() {
        return mBestScore;
    }

    public void setmBestScore(int mBestScore) {
        this.mBestScore = mBestScore;
    }

    @Override
    public String toString() {

        String s = "User{" +
                "mFirstname='" + mFirstname + '\'' +
                '}';

        if(getmBestScore() != 0){
            s = "User{" +
                    "mFirstname='" + mFirstname + '\'' +
                    "Best score='" + mBestScore + '\'' +
                    '}';
        }
        return s;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getFirstname());
        dest.writeInt(getmBestScore());
    }
}
