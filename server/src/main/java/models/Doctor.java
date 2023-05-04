package models;

import java.io.Serializable;

public class Doctor extends Admin implements Serializable {
    String education;
    String speciality;
    String expiriense;
    String university;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getExpiriense() {
        return expiriense;
    }

    public void setExpiriense(String expiriense) {
        this.expiriense = expiriense;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    double rating;
}
