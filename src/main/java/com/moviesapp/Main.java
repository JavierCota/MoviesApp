package com.moviesapp;

import com.moviesapp.controller.MainView;

public class Main {
    private MainView view;
    public static void main(String[] args) {
        DBManager.initConnection();
        new MainView();
    }
}