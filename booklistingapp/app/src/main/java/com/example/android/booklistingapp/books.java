/**
 * Created by Koshika Gupta on 17-03-2018.
 */
package com.example.android.booklistingapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.security.Policy;

public class books implements Parcelable {
    String ctitle;
    String cauthor;

    public books(String title, String author) {
        ctitle = title;
        cauthor = author;
    }

    public String getTitle() {
        return ctitle;
    }

    public String getAuthor() {
        return cauthor;
    }

    protected books(Parcel in) {
        cauthor = in.readString();
        ctitle = in.readString();
    }

    public static final Creator<books> CREATOR = new Creator<books>() {
        @Override
        public books createFromParcel(Parcel in) {
            return new books(in);
        }

        @Override
        public books[] newArray(int size) {
            return new books[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cauthor);
        parcel.writeString(ctitle);
    }
}
