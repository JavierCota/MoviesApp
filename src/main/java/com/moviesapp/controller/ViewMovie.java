package com.moviesapp.controller;

import com.moviesapp.model.crud.CRUDMovie;
import com.moviesapp.model.internal.Movie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewMovie extends javax.swing.JFrame {
    private JTextField viewMName;
    private JTextField viewMGenre;
    private JTextField viewMDuration;
    private JTextField viewMClassification;
    private JTextField viewMReleaseD;
    private JTextField viewMDescription;
    private JLabel viewMDirID;
    private JLabel viewMStudioID;
    private JPanel viewMPanel;
    private JButton viewMReturnBtn;
    private JButton viewMUpdateBtn;
    private JButton viewMDeleteBtn;
    private JLabel viewMovieID;
    private CRUDMovie crudMovie = new CRUDMovie();

    public ViewMovie(Movie movie){

        JFrame frame = new JFrame("MovieApp");
        frame.setContentPane(viewMPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        viewMName.setText(movie.getMovieName());
        viewMGenre.setText(movie.getGenre());
        viewMDuration.setText(movie.getDuration().toString());
        viewMClassification.setText(movie.getClassification());
        viewMReleaseD.setText(movie.getReleaseDate().toString());
        viewMDescription.setText(movie.getDescription());
        viewMovieID.setText(movie.getMovieID().toString());
        viewMDirID.setText(movie.getDirectorID().toString());
        viewMStudioID.setText(movie.getStudioID().toString());

        viewMReturnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView objMainView = new MainView();
                frame.setContentPane(objMainView.getContentPane());
                frame.dispose();
            }
        });

        viewMDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this register?","Delete register", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    crudMovie.deleteMovie(movie.getMovieID());
                    MainView objMainView = new MainView();
                    frame.setContentPane(objMainView.getContentPane());
                    frame.dispose();
                }
            }
        });

        viewMUpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null,"Are you sure you want to update this register?","Update register", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    java.sql.Date sqlDate = java.sql.Date.valueOf(viewMReleaseD.getText());
                    movie.setMovieName(viewMName.getText());
                    movie.setGenre(viewMGenre.getText());
                    movie.setDuration(Integer.parseInt(viewMDuration.getText()));
                    movie.setClassification(viewMClassification.getText());
                    movie.setReleaseDate(sqlDate);
                    movie.setDescription(viewMDescription.getText());
                    crudMovie.updateMovie(movie);
                }
            }
        });
    }
}
