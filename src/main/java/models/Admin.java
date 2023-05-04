package models;

import java.io.Serializable;

public class Admin implements Serializable {
    String name;
    String sur_name;
    String patronymic;
    String phone;
    String ID_passport;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSur_name() {
        return sur_name;
    }

    public void setSur_name(String sur_name) {
        this.sur_name = sur_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getID_passport() {
        return ID_passport;
    }

    public void setID_passport(String ID_passport) {
        this.ID_passport = ID_passport;
    }

    public String getSeria_number() {
        return seria_number;
    }

    public void setSeria_number(String seria_number) {
        this.seria_number = seria_number;
    }

    public String getDate_registration() {
        return date_registration;
    }

    public void setDate_registration(String date_registration) {
        this.date_registration = date_registration;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    String seria_number;
    String date_registration;
    int id_user;
}
