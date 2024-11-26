package com.example.swen766_bettermaps.data;

import android.os.Parcel;
import android.os.Parcelable;

public class RITUser implements Parcelable {
    private String RIT_id;
    private String first_name;
    private String email;

    public RITUser(String RIT_id, String first_name, String email) {
        this.RIT_id = RIT_id;
        this.first_name = first_name;
        this.email = email;
    }

    protected RITUser(Parcel in) {
        RIT_id = in.readString();
        first_name = in.readString();
        email = in.readString();
    }

    public static final Creator<RITUser> CREATOR = new Creator<RITUser>() {
        @Override
        public RITUser createFromParcel(Parcel in) {
            return new RITUser(in);
        }

        @Override
        public RITUser[] newArray(int size) {
            return new RITUser[size];
        }
    };

    public String getRIT_id() {
        return RIT_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(RIT_id);
        dest.writeString(first_name);
        dest.writeString(email);
    }
}