package org.example;

import java.io.Serializable;

public class SlackMessage implements Serializable {

    private String username="";
    private String text="";
    private String icon_emoji="";

    public SlackMessage() {

    }

    public SlackMessage(String username, String text, String icon_emoji) {
        this.username = username;
        this.text = text;
        this.icon_emoji = icon_emoji;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon_emoji() {
        return icon_emoji;
    }

    public void setIcon_emoji(String icon_emoji) {
        this.icon_emoji = icon_emoji;
    }
}