package com.napas.paytouch.model;

import com.google.gson.annotations.SerializedName;

public class Filmography {

    @SerializedName("adult")
    Boolean adult;
    @SerializedName("backdrop_path")
    String backdropPath;
    @SerializedName("id")
    Integer id;
    @SerializedName("original_title")
    String originalTitle;
    @SerializedName("release_date")
    Long releaseDate;
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("popularity")
    Double popularity;
    @SerializedName("title")
    String title;
    @SerializedName("video")
    Boolean video;
    @SerializedName("vote_average")
    Float voteAverage;
    @SerializedName("vote_count")
    Integer voteCount;
    @SerializedName("media_type")
    String mediaType;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
