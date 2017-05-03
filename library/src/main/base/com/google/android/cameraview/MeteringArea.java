package com.google.android.cameraview;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by fpilz on 25/04/2017.
 */

public class MeteringArea implements Parcelable {

    public static Rect toRect(@NonNull MeteringArea meteringArea){
        return new Rect(meteringArea.left, meteringArea.top, meteringArea.right, meteringArea.bottom);
    }
    public int left, top, right, bottom;

    public MeteringArea(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public MeteringArea(Rect focusArea) {
        left = focusArea.left;
        top = focusArea.top;
        right = focusArea.right;
        bottom = focusArea.bottom;
    }
    public static Rect convertToSensorCoordinates(MeteringArea ma, int sensorWidth, int sensorHeight){
        // normalise rect dimensions from [-1000, 1000] into [0,1000]
        int left = (ma.left + 1000) / 2;
        int top = (ma.top + 1000) / 2;
        int right = (ma.right + 1000) / 2;
        int bottom = (ma.bottom +1000) / 2;

        // map metering area to view width and height
        left = Math.round(left * sensorWidth / 1000);
        right = Math.round(right * sensorWidth / 1000);
        top = Math.round(top * sensorHeight / 1000);
        bottom = Math.round(bottom * sensorHeight / 1000);

        return new Rect(left, top, right, bottom);
    }

    public static final Creator<MeteringArea> CREATOR = new Creator<MeteringArea>() {
        @Override
        public MeteringArea createFromParcel(Parcel in) {
            int left = in.readInt();
            int top = in.readInt();
            int right = in.readInt();
            int bottom = in.readInt();

            return new MeteringArea(left, top, right, bottom);
        }

        @Override
        public MeteringArea[] newArray(int size) {
            return new MeteringArea[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(left);
        dest.writeInt(top);
        dest.writeInt(right);
        dest.writeInt(bottom);
    }
}
