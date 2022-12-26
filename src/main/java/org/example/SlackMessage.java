package org.example;

import java.io.Serializable;

public class SlackMessage implements Serializable {

    private String text="";
    private String token="";
    private String channel="";
    private String btn1_name=null;
    private String btn2_name=null;
    private String btn1_url=null;
    private String btn2_url=null;
    private String url=null;
    private String image_url=null;
    private String image_alt="";

    public String getImage_alt() {
        return image_alt;
    }

    public void setImage_alt(String image_alt) {
        this.image_alt = image_alt;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBtn1_url() {
        return btn1_url;
    }

    public void setBtn1_url(String btn1_url) {
        this.btn1_url = btn1_url;
    }

    public String getBtn2_url() {
        return btn2_url;
    }

    public void setBtn2_url(String btn2_url) {
        this.btn2_url = btn2_url;
    }

    public String getBtn1_name() {
        return btn1_name;
    }

    public void setBtn1_name(String btn1_name) {
        this.btn1_name = btn1_name;
    }

    public String getBtn2_name() {
        return btn2_name;
    }

    public void setBtn2_name(String btn2_name) {
        this.btn2_name = btn2_name;
    }

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