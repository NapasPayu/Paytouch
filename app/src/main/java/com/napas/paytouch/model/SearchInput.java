package com.napas.paytouch.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchInput implements Parcelable {

    String name;
    String location;
    Boolean top;
    Double minPopularity;
    Double maxPopularity;

    public SearchInput() {
    }

    public SearchInput(String name, String location, Boolean top, Double minPopularity, Double maxPopularity) {
        this.name = name;
        this.location = location;
        this.top = top;
        this.minPopularity = minPopularity;
        this.maxPopularity = maxPopularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public Double getMinPopularity() {
        return minPopularity;
    }

    public void setMinPopularity(Double minPopularity) {
        this.minPopularity = minPopularity;
    }

    public Double getMaxPopularity() {
        return maxPopularity;
    }

    public void setMaxPopularity(Double maxPopularity) {
        this.maxPopularity = maxPopularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.location);
        dest.writeValue(this.top);
        dest.writeValue(this.minPopularity);
        dest.writeValue(this.maxPopularity);
    }

    protected SearchInput(Parcel in) {
        this.name = in.readString();
        this.location = in.readString();
        this.top = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.minPopularity = (Double) in.readValue(Double.class.getClassLoader());
        this.maxPopularity = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchInput> CREATOR = new Parcelable.Creator<SearchInput>() {
        @Override
        public SearchInput createFromParcel(Parcel source) {
            return new SearchInput(source);
        }

        @Override
        public SearchInput[] newArray(int size) {
            return new SearchInput[size];
        }
    };
}
