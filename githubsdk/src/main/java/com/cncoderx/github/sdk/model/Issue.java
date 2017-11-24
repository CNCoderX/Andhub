package com.cncoderx.github.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * @author cncoderx
 */
public class Issue implements Parcelable {
    public int id;
    public int number;
    public String state;
    public String title;
    public String body;
    public User user;
    public User assignee;
    public List<User> assignees;
    public List<Label> labels;
    public Milestone milestone;
    public boolean locked;
    public int comments;
    @SerializedName("closed_at")
    public Date closedAt;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;

    public Issue() {

    }

    protected Issue(Parcel in) {
        id = in.readInt();
        number = in.readInt();
        state = in.readString();
        title = in.readString();
        body = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        assignee = in.readParcelable(User.class.getClassLoader());
        assignees = in.createTypedArrayList(User.CREATOR);
        labels = in.createTypedArrayList(Label.CREATOR);
        milestone = in.readParcelable(Milestone.class.getClassLoader());
        locked = in.readByte() != 0;
        comments = in.readInt();
        closedAt = DateParcel.readDate(in);
        createdAt = DateParcel.readDate(in);
        updatedAt = DateParcel.readDate(in);
    }

    public static final Creator<Issue> CREATOR = new Creator<Issue>() {
        @Override
        public Issue createFromParcel(Parcel in) {
            return new Issue(in);
        }

        @Override
        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(number);
        dest.writeString(state);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(assignee, flags);
        dest.writeTypedList(assignees);
        dest.writeTypedList(labels);
        dest.writeParcelable(milestone, flags);
        dest.writeByte((byte) (locked ? 1 : 0));
        dest.writeInt(comments);
        DateParcel.writeDate(closedAt, dest);
        DateParcel.writeDate(createdAt, dest);
        DateParcel.writeDate(updatedAt, dest);
    }
}
