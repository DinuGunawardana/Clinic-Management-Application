package com.example.clinicapp.Model;

public class Patients {

    String userName, Name, age, gender, nic, address, telno, diagnosis, date;

    public Patients() {
    }

    public Patients(String userName, String name, String age, String gender, String nic, String address, String telno, String diagnosis, String date) {
        this.userName = userName;
        Name = name;
        this.age = age;
        this.gender = gender;
        this.nic = nic;
        this.address = address;
        this.telno = telno;
        this.diagnosis = diagnosis;
        this.date = date;
    }

    public Patients(String userName, String name, String age, String nic, String address, String telno, String diagnosis, String date) {
        this.userName = userName;
        Name = name;
        this.age = age;
        this.nic = nic;
        this.address = address;
        this.telno = telno;
        this.diagnosis = diagnosis;
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
