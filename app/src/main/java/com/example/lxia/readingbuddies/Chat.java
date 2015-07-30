package com.example.lxia.readingbuddies;

/**
 * Created by v-lesh on 7/30/2015.
 */
public class Chat {

    private String fromUserId;
    private String toUserId;
    private String content;
    private String time;

    public Chat() {
    }

    public Chat(String fromUserId, String toUserId, String content, String time) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.content = content;
        this.time = time;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
