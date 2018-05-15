package com.llx278.demo.learnbinder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by llx on 2018/4/11.
 */

public class Event implements Parcelable {
    private String para1;
    private int para2;

    protected Event(Parcel in) {
        para1 = in.readString();
        para2 = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public int getPara2() {
        return para2;
    }

    public void setPara2(int para2) {
        this.para2 = para2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(para1);
        dest.writeInt(para2);
    }
}
