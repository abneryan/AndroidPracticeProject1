package com.example.service.beans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/6 00:53
 * @Description:
 */
public class Rect implements Parcelable {
    public int left;
    public int top;
    public int right;
    public int bottom;

    public Rect() {
    }
    private Rect(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Rect> CREATOR = new Creator<Rect>() {
        @Override
        public Rect createFromParcel(Parcel in) {
            return new Rect(in);
        }

        @Override
        public Rect[] newArray(int size) {
            return new Rect[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(left);
        parcel.writeInt(top);
        parcel.writeInt(right);
        parcel.writeInt(bottom);
    }
    public void readFromParcel(Parcel in) {
        left = in.readInt();
        top = in.readInt();
        right = in.readInt();
        bottom = in.readInt();
    }

    @Override
    public String toString() {
        return "Rect{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                '}';
    }
}
