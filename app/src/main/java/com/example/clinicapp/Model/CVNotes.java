package com.example.clinicapp.Model;

public class CVNotes {

    String patientid;
    String notes_content_text;
    String datebtn;
    String date;

    public CVNotes() {
    }

    public CVNotes(String patientid, String notes_content_text, String datebtn, String date) {
        this.patientid = patientid;
        this.notes_content_text = notes_content_text;
        this.datebtn = datebtn;
        this.date = date;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getNotes_content_text() {
        return notes_content_text;
    }

    public void setNotes_content_text(String notes_content_text) {
        this.notes_content_text = notes_content_text;
    }

    public String getDatebtn() {
        return datebtn;
    }

    public void setDatebtn(String datebtn) {
        this.datebtn = datebtn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
