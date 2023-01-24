package com.moviesapp.controller;

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

    public MainView() {

        JFrame frame = new JFrame("MovieApp");
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText("Test registerButton");
            }
        });
    }
}
