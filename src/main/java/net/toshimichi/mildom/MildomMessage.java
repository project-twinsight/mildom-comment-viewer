package net.toshimichi.mildom;

import com.google.gson.annotations.SerializedName;

final public class MildomMessage {

    @SerializedName("msg")
    private String message;
    private String userName;
    private int level;

    public String getUserName() {
        return userName;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "MildomMessage{" +
                "message='" + message + '\'' +
                ", username='" + userName + '\'' +
                ", level=" + level +
                '}';
    }
}
