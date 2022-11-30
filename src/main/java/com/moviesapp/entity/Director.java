package com.moviesapp.entity;

import java.util.Date;
import java.util.Objects;

public class Director {
    private String directorName;
    private Date birthDate;
    private String nationality;
    private String activeYears;
    private String education;

    public Director(String directorName, Date birthDate, String nationality, String activeYears, String education) {
        this.directorName = directorName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.activeYears = activeYears;
        this.education = education;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getActiveYears() {
        return activeYears;
    }

    public void setActiveYears(String activeYears) {
        this.activeYears = activeYears;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Director)) return false;
        Director director = (Director) o;
        return directorName.equals(director.directorName) && birthDate.equals(director.birthDate) && Objects.equals(nationality, director.nationality) && Objects.equals(activeYears, director.activeYears) && Objects.equals(education, director.education);
    }
    @Override
    public int hashCode() {
        return Objects.hash(directorName, birthDate, nationality, activeYears, education);
    }
    @Override
    public String toString() {
        return "Director{" +
                "directorName='" + directorName + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", activeYears='" + activeYears + '\'' +
                ", education='" + education + '\'' +
                '}';
    }
}
