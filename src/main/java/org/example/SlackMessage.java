package org.example;

import java.io.Serializable;

public class SlackMessage implements Serializable {

    private String text="";
    private String token="";
    private String channel="";

    public SlackMessage() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}