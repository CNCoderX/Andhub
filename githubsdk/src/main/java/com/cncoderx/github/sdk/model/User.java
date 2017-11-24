package com.cncoderx.github.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author cncoderx
 */
public class User implements Parcelable {
    public int id;
    public String login;
    @SerializedName("gravatar_id")
    public String avatarId;
    @SerializedName("avatar_url")
    public String avatarUrl;
    public String type; // user or organization
    @SerializedName("description")
    public String desc; // only for organization

    public User() {
    }

    protected User(Parcel in) {
        id = in.readInt();
        login = in.readString();
        avatarId = in.readString();
        avatarUrl = in.readString();
        type = in.readString();
        desc = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeString(avatarId);
        dest.writeString(avatarUrl);
        dest.writeString(type);
        dest.writeString(desc);
    }
}
