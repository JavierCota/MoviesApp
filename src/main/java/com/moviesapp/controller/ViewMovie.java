package com.moviesapp.controller;

import com.moviesapp.model.crud.CRUDMovie;
import com.moviesapp.model.internal.Movie;
import com.moviesapp.util.TextFieldValidations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

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
    private TextFieldValidations validations = new TextFieldValidations();
    private CRUDMovie crudMovie = new CRUDMovie();
    private Logger LOGGER = Logger.getLogger(ViewMovie.class.getName());

    public ViewMovie(Movie movie) {

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
                LOGGER.info("Returning to MainView.");
                MainView objMainView = new MainView();
                frame.setContentPane(objMainView.getContentPane());
                frame.dispose();
            }
        });

        viewMDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this registered movie?", "Delete register", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    LOGGER.info("Attempting to delete " + movie.getMovieName() + " ID: " + movie.getMovieID());
                    try {
                        crudMovie.deleteMovie(movie.getMovieID());
                        LOGGER.info("Movie deleted successfully");
                        JOptionPane.showMessageDialog(null, "The movie was deleted successfully", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception exception) {
                        LOGGER.warning(exception.getMessage());
                        JOptionPane.showMessageDialog(null, "An error occurred while deleting the movie " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    MainView objMainView = new MainView();
                    frame.setContentPane(objMainView.getContentPane());
                    frame.dispose();
                }
            }
        });

        viewMUpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this registered movie?", "Update register", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    LOGGER.info("Attempting to update " + movie.getMovieName() + " ID: " + movie.getMovieID());
                    if (viewMName.getText().isEmpty()) {
                        LOGGER.warning("Unable to update movie, viewMName is empty.");
                        JOptionPane.showMessageDialog(null, "Please make sure Name is not empty.", "Message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        java.sql.Date sqlDate = java.sql.Date.valueOf(viewMReleaseD.getText());
                        movie.setMovieName(viewMName.getText());
                        movie.setGenre(viewMGenre.getText());
                        movie.setDuration(Integer.parseInt(viewMDuration.getText()));
                        movie.setClassification(viewMClassification.getText());
                        movie.setReleaseDate(sqlDate);
                        movie.setDescription(viewMDescription.getText());
                        try {
                            crudMovie.updateMovie(movie);
                            LOGGER.info("Movie updated successfully");
                            JOptionPane.showMessageDialog(null, "Movie updated successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception exception) {
                            LOGGER.warning(exception.getMessage());
                            JOptionPane.showMessageDialog(null, "Movie was not updated " + exception.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        validateMovie(viewMName, viewMGenre, viewMDuration, viewMClassification, viewMReleaseD, viewMDescription);
    }

    public void validateMovie(JTextField name, JTextField genre, JTextField duration, JTextField classification, JTextField releaseDate, JTextField description) {
        validations.validateStringChars(name);
        validations.validateStringChars(genre);
        validations.validateIntChars(duration);
        validations.validateStringChars(classification);
        validations.validateDateChars(releaseDate);
        validations.validateStringChars(description);
        validations.validateNameLength(name);
        validations.validateTextFLength(genre);
        validations.validateIntDateLength(duration);
        validations.validateTextFLength(classification);
        validations.validateIntDateLength(releaseDate);
        validations.validateDescriptionLength(description);
    }
}
