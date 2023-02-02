package com.moviesapp;

import com.moviesapp.controller.MainView;
import com.moviesapp.model.util.DBManager;

public class Main {
    private MainView view;
    public static void main(String[] args) {
        new DBManager().connect();
        new MainView();
    }
}