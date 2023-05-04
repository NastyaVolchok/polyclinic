package models;

import java.io.Serializable;

public class History implements Serializable {
    int id;
    int id_user;
    String status;
    String speciality;

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(String id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFIOdoctor() {
        return FIOdoctor;
    }

    public void setFIOdoctor(String FIOdoctor) {
        this.FIOdoctor = FIOdoctor;
    }

    String id_doctor;
    String date;
    String FIOdoctor;
}
