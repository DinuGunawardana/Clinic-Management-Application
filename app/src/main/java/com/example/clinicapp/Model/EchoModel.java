package com.example.clinicapp.Model;

public class EchoModel {

    String userId;
    String echo;
    String echoImage;
    String date;

    public EchoModel(String userId, String echo, String echoImage, String date) {
        this.userId = userId;
        this.echo = echo;
        this.echoImage = echoImage;
        this.date = date;
    }

    public EchoModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEcho() {
        return echo;
    }

    public void setEcho(String echo) {
        this.echo = echo;
    }

    public String getEchoImage() {
        return echoImage;
    }

    public void setEchoImage(String echoImage) {
        this.echoImage = echoImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
