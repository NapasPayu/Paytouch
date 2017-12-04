package com.napas.paytouch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Actor implements Parcelable {

    String description;
    Integer identifier;
    @SerializedName("profile_path")
    String profilePath;
    Double popularity;
    Boolean adult;
    Boolean top;
    String name;
    String location;
    @SerializedName("known_for")
    List<Filmography> knownFor;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
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

    public List<Filmography> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<Filmography> knownFor) {
        this.knownFor = knownFor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeValue(this.identifier);
        dest.writeString(this.profilePath);
        dest.writeValue(this.popularity);
        dest.writeValue(this.adult);
        dest.writeValue(this.top);
        dest.writeString(this.name);
        dest.writeString(this.location);
        dest.writeList(this.knownFor);
    }

    public Actor() {
    }

    protected Actor(Parcel in) {
        this.description = in.readString();
        this.identifier = (Integer) in.readValue(Integer.class.getClassLoader());
        this.profilePath = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.top = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.name = in.readString();
        this.location = in.readString();
        this.knownFor = new ArrayList<Filmography>();
        in.readList(this.knownFor, Filmography.class.getClassLoader());
    }

    public static final Parcelable.Creator<Actor> CREATOR = new Parcelable.Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel source) {
            return new Actor(source);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };
}
