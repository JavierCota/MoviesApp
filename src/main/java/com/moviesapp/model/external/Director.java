package com.moviesapp.model.external;

import java.sql.Date;
import java.util.Objects;

public class Director {
    private String directorName;
    private Date birthDate;
    private String nationality;
    private String activeYears;
    private String favoriteGenre;

    public Director(String directorName, Date birthDate, String nationality, String activeYears, String favoriteGenre) {
        this.directorName = directorName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.activeYears = activeYears;
        this.favoriteGenre = favoriteGenre;
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

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Director)) return false;
        Director director = (Director) o;
        return directorName.equals(director.directorName) && Objects.equals(birthDate, director.birthDate) && Objects.equals(nationality, director.nationality) && Objects.equals(activeYears, director.activeYears) && Objects.equals(favoriteGenre, director.favoriteGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorName, birthDate, nationality, activeYears, favoriteGenre);
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorName='" + directorName + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", activeYears='" + activeYears + '\'' +
                ", favoriteGenre='" + favoriteGenre + '\'' +
                '}';
    }
}
