package com.moviesapp.model.internal;


import java.sql.Date;
import java.util.Objects;

public class Movie {
    private String movieName;
    private String genre;
    private Integer duration;
    private String classification;
    private Date releaseDate;
    private String description;
    private Integer movieID;
    private Integer directorID;
    private Integer studioID;

    public Movie(String movieName, String genre, Integer duration, String classification, Date releaseDate, String description, Integer movieID, Integer directorID, Integer studioID) {
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
        this.classification = classification;
        this.releaseDate = releaseDate;
        this.description = description;
        this.movieID = movieID;
        this.directorID = directorID;
        this.studioID = studioID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public Integer getDirectorID() {
        return directorID;
    }

    public void setDirectorID(Integer directorID) {
        this.directorID = directorID;
    }

    public Integer getStudioID() {
        return studioID;
    }

    public void setStudioID(Integer studioID) {
        this.studioID = studioID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return movieName.equals(movie.movieName) && Objects.equals(genre, movie.genre) && Objects.equals(duration, movie.duration) && Objects.equals(classification, movie.classification) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(description, movie.description) && movieID.equals(movie.movieID) && directorID.equals(movie.directorID) && studioID.equals(movie.studioID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, genre, duration, classification, releaseDate, description, movieID, directorID, studioID);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieName='" + movieName + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", classification='" + classification + '\'' +
                ", releaseDate=" + releaseDate +
                ", description='" + description + '\'' +
                ", movieID=" + movieID +
                ", directorID=" + directorID +
                ", studioID=" + studioID +
                '}';
    }
}
