package com.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class GameGson implements Parcelable {

    /**
     * name : bobo
     * en_name : bobo
     * isvertial : true
     * path : games/game_bobo/index.html
     */

    private String name;
    private String en_name;
    private boolean isvertial;
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public boolean isIsvertial() {
        return isvertial;
    }

    public void setIsvertial(boolean isvertial) {
        this.isvertial = isvertial;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.en_name);
        dest.writeByte(this.isvertial ? (byte) 1 : (byte) 0);
        dest.writeString(this.path);
    }

    public GameGson() {
    }

    protected GameGson(Parcel in) {
        this.name = in.readString();
        this.en_name = in.readString();
        this.isvertial = in.readByte() != 0;
        this.path = in.readString();
    }

    public static final Parcelable.Creator<GameGson> CREATOR = new Parcelable.Creator<GameGson>() {
        @Override
        public GameGson createFromParcel(Parcel source) {
            return new GameGson(source);
        }

        @Override
        public GameGson[] newArray(int size) {
            return new GameGson[size];
        }
    };
}
