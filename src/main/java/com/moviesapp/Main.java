package com.moviesapp;

import com.moviesapp.controller.MainView;
import com.moviesapp.model.entity.Studio;

public class Main {
    private MainView view;
    public static void main(String[] args) {
        DBManager.initConnection();
        new MainView();
    }
}