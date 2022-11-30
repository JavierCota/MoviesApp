package com.moviesapp;

import com.moviesapp.entity.Movie;

import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date date = new Date(2022,11,29);
        Movie newMovie = new Movie("Black Panther Wakanda Forever", "Action", 161, "m", date, "pantera negra", Arrays.asList(455454500), Arrays.asList(645600));
    }
}