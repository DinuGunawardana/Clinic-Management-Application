package com.example.clinicapp.Model;

public class ModelAngiogram2 {

    String userId;
    String angiogram2;
    String angiogram2Image;
    String date;

    public ModelAngiogram2() {
    }

    public ModelAngiogram2(String userId, String angiogram2, String angiogram2Image, String date) {
        this.userId = userId;
        this.angiogram2 = angiogram2;
        this.angiogram2Image = angiogram2Image;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAngiogram2() {
        return angiogram2;
    }

    public void setAngiogram2(String angiogram2) {
        this.angiogram2 = angiogram2;
    }

    public String getAngiogram2Image() {
        return angiogram2Image;
    }

    public void setAngiogram2Image(String angiogram2Image) {
        this.angiogram2Image = angiogram2Image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
