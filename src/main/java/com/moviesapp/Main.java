package com.moviesapp;

import com.moviesapp.controller.MainView;
import java.util.logging.Logger;

public class Main {
    private MainView view;
    private static Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        LOGGER.info("Starting application.");
        new MainView();
    }
}