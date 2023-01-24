package com.moviesapp;

import com.moviesapp.controller.MainView;
import com.moviesapp.model.entity.Director;
import com.moviesapp.model.entity.Movie;
import com.moviesapp.model.entity.Studio;

import javax.swing.*;
import java.util.Arrays;


public class Main {
    private MainView view;
    public static void main(String[] args) {
        /*java.sql.Date sqlDate = java.sql.Date.valueOf("2020-10-27");
        Director newDirector = new Director("Wuicho", sqlDate, "Mexican", "1996-present", "Action", 135465321);
        System.out.println(newDirector);
        Studio newStudio = new Studio("CharlieKobe", "CinematicArtz", sqlDate, "Maximiliano II", "Hermosillo Desert", 165465);
        System.out.println(newStudio);
        Movie newMovie = new Movie("Black Panther Wakanda Forever", "Action", 161, "m", sqlDate, "pantera negra", Arrays.asList(newDirector.getDirectorID()), Arrays.asList(newStudio.getStudioID()));
        System.out.println(newMovie);
        */
        new MainView();
    }
}