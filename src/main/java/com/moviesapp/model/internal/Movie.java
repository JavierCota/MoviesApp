package com.moviesapp.model.internal;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Movie {
    private String movieName;
    private String genre;
    private Integer duration;
    private String classification;
    private Date releaseDate;
    private String description;
    private List<Integer> directorIDs;
    private List<Integer> studioIDs;

    public Movie(String movieName, String genre, Integer duration, String classification, Date releaseDate, String description, List<Integer> directorIDs, List<Integer> studioIDs) {
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
        this.classification = classification;
        this.releaseDate = releaseDate;
        this.description = description;
        this.directorIDs = directorIDs;
        this.studioIDs = studioIDs;
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
    public List<Integer> getDirectorIDs() {
        return directorIDs;
    }

    public void setDirectorIDs(List<Integer> directorIDs) {
        this.directorIDs = directorIDs;
    }

    public List<Integer> getStudioIDs() {
        return studioIDs;
    }

    public void setStudioIDs(List<Integer> studioIDs) {
        this.studioIDs = studioIDs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return movieName.equals(movie.movieName) && Objects.equals(genre, movie.genre) && Objects.equals(duration, movie.duration) && Objects.equals(classification, movie.classification) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(description, movie.description) && directorIDs.equals(movie.directorIDs) && studioIDs.equals(movie.studioIDs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, genre, duration, classification, releaseDate, description, directorIDs, studioIDs);
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
                ", directorIDs=" + directorIDs +
                ", studioIDs=" + studioIDs +
                '}';
    }
}
