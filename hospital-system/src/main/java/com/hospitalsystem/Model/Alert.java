package com.hospitalsystem.Model;

public class Alert {
    private int id;
    private String type;
    private String message;
    private String doctor;
    private String data;

    public Alert(String type, String message, String doctor, String data) {
        this.type = type;
        this.message = message;
        this.doctor = doctor;
        this.data = data;
    }

    public Alert(int id, String type, String message, String doctor, String data) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.doctor = doctor;
        this.data = data;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return data;
    }
    public void setDate(String data) {
        this.data = data;
    }
}
