package com.example.clinicapp.Model;

public class User {

    // Creating private variables to store user data
    // private variables for encapsulation
    public String fullName, email , profile, occupation;
    public String journalNote;
    public int sugIndex;

    public User() {
    }

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public User(String fullName, String email, String occupation) {
        this.fullName = fullName;
        this.email = email;
        this.occupation = occupation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJournalNote() {
        return journalNote;
    }

    public void setJournalNote(String journalNote) {
        this.journalNote = journalNote;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getSugIndex() {
        return sugIndex;
    }

    public void setSugIndex(int sugIndex) {
        this.sugIndex = sugIndex;
    }

}
