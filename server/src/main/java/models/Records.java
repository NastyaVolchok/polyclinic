package models;

import java.io.Serializable;
import java.util.Date;

public class Records implements Serializable {
    String idDoctor;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int idUser;
    String date;
    String status;
    String dateRecords;

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateRecords() {
        return dateRecords;
    }

    public void setDateRecords(String dateRecords) {
        this.dateRecords = dateRecords;
    }
}
