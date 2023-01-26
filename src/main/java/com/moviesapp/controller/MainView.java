package com.moviesapp.controller;

import com.moviesapp.model.entity.Director;
import com.moviesapp.model.entity.crud.CRUDDirector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView{
    private JButton registerButton;
    private JLabel label1;
    private JPanel panel1;
    private JTextField descriptionReg;
    private JTextField releaseDateReg;
    private JTextField movieNameReg;
    private JTextField genreReg;
    private JTextField durationReg;
    private JTextField classificationReg;
    private JPanel registerPanel;
    private JTabbedPane mainPanel;
    private JTextField movieNameSearch;
    private JTextField genreSearch;
    private JButton searchButton;
    private JTextField directorNameIn;
    private JTextField birthDateIn;
    private JTextField nationalityIn;
    private JTextField activeYearsIn;
    private JTextField favoriteGenreIn;
    private JPanel searchPanel;
    private JPanel directorRSPanel;
    private JButton registerDirButton;
    private JButton searchDirButton;
    private JTextField studioNameIn;
    private JTextField studioIndustryIn;
    private JTextField studioFoundationIn;
    private JTextField studioFounderIn;
    private JTextField studioHQIn;
    private JPanel studioRSPanel;
    private JButton searchStudioButton;
    private JButton registerStudioButton;
    private JTextField directorIDIn;
    private JTextField studioIDIn;
    private CRUDDirector crudDirector;

    public MainView() {

        JFrame frame = new JFrame("MovieApp");
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        registerDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.sql.Date sqlDate = java.sql.Date.valueOf(birthDateIn.getText());
                Director newDirector = new Director(
                        directorNameIn.getText(), sqlDate, nationalityIn.getText(),activeYearsIn.getText(),favoriteGenreIn.getText(), Integer.parseInt(directorIDIn.getText()));
                System.out.println(newDirector);
                directorNameIn.setText("");
                birthDateIn.setText("");
                nationalityIn.setText("");
                activeYearsIn.setText("");
                favoriteGenreIn.setText("");
                directorIDIn.setText("");
                crudDirector.createDirector(newDirector);
            }
        });
    }
}
