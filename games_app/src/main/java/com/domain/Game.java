package com.domain;


import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {

    private String name;
    private String enName;
    private String path;
    private String indexUrl;
    private String iconImgUrl;

    public Game() {
    }

    public Game(String name, String enName, String path, String indexUrl) {
        this.name = name;
        this.enName = enName;
        this.path = path;
        this.indexUrl = indexUrl;
    }

    public String getUrl(){
        String absUrl = "games/"+path+"/"+indexUrl;
        return absUrl;
    }

    public String getResultName(){

        return getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public String getIconImgUrl() {
        return iconImgUrl;
    }

    public void setIconImgUrl(String iconImgUrl) {
        this.iconImgUrl = iconImgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.enName);
        dest.writeString(this.path);
        dest.writeString(this.indexUrl);
        dest.writeString(this.iconImgUrl);
    }

    protected Game(Parcel in) {
        this.name = in.readString();
        this.enName = in.readString();
        this.path = in.readString();
        this.indexUrl = in.readString();
        this.iconImgUrl = in.readString();
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
