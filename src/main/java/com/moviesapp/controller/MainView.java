package com.moviesapp.controller;

import com.moviesapp.model.crud.CRUDDirector;
import com.moviesapp.model.crud.CRUDStudio;
import com.moviesapp.model.external.Director;
import com.moviesapp.model.external.Studio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainView extends javax.swing.JFrame {
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
    private JTextField nameSearch;
    private JButton searchButton;
    private JTextField directorNameIn;
    private JTextField birthDateIn;
    private JComboBox nationalityIn;
    private JTextField activeYearsIn;
    private JTextField favoriteGenreIn;
    private JPanel searchPanel;
    private JPanel directorRSPanel;
    private JButton registerDirButton;
    private JTextField studioNameIn;
    private JTextField studioIndustryIn;
    private JTextField studioFoundationIn;
    private JTextField studioFounderIn;
    private JTextField studioHQIn;
    private JPanel studioRSPanel;
    private JButton registerStudioButton;
    private JComboBox searchSelection;
    private JComboBox studioReg;
    private JComboBox directorReg;
    private CRUDDirector crudDirector = new CRUDDirector();
    private CRUDStudio crudStudio = new CRUDStudio();


    public MainView() {

        JFrame mainFrame = new JFrame("MovieApp");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();

        //Set nationalities in nationalityIn JComboBox
        nationalityJComboB(nationalityIn);

        //Set directors in directorReg JComboBox
        directorJComboB(directorReg);

        //Set studios in studioReg JComboBox
        studioJComboB(studioReg);

        //Set options in searchSelection JComboBox
        searchSelection.addItem("Movie");
        searchSelection.addItem("Director");
        searchSelection.addItem("Studio");
        searchSelection.setSelectedIndex(-1);

        registerDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.sql.Date sqlDate = java.sql.Date.valueOf(birthDateIn.getText());
                com.moviesapp.model.external.Director newDirector = new Director(
                        directorNameIn.getText(), sqlDate, (String) nationalityIn.getSelectedItem(), activeYearsIn.getText(), favoriteGenreIn.getText());
                System.out.println(newDirector);
                directorNameIn.setText("");
                birthDateIn.setText("");
                nationalityIn.setSelectedIndex(-1);
                activeYearsIn.setText("");
                favoriteGenreIn.setText("");
                crudDirector.createDirector(newDirector);
            }
        });

        registerStudioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.sql.Date sqlDate = java.sql.Date.valueOf(studioFoundationIn.getText());
                com.moviesapp.model.external.Studio newStudio = new Studio(
                        studioNameIn.getText(), studioIndustryIn.getText(), sqlDate, studioFounderIn.getText(), studioHQIn.getText());
                System.out.println(newStudio);
                studioNameIn.setText("");
                studioIndustryIn.setText("");
                studioFoundationIn.setText("");
                studioFounderIn.setText("");
                studioHQIn.setText("");
                crudStudio.createStudio(newStudio);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = nameSearch.getText();
                if (searchSelection.getSelectedItem().equals("Director")) {
                    com.moviesapp.model.internal.Director director = crudDirector.readDirector(search);
                    if (director == null) {
                        JOptionPane.showMessageDialog(null, "Register not found ", "Message", JOptionPane.ERROR_MESSAGE);
                        nameSearch.setText("");
                    } else {
                        ViewDirector objViewDirector = new ViewDirector(director);
                        mainFrame.setContentPane(objViewDirector.getContentPane());
                        mainFrame.dispose();
                    }
                }
                if (searchSelection.getSelectedItem().equals("Studio")) {
                    com.moviesapp.model.internal.Studio studio = crudStudio.readStudio(search);
                    if (studio == null) {
                        JOptionPane.showMessageDialog(null, "Register not found ", "Message", JOptionPane.ERROR_MESSAGE);
                        nameSearch.setText("");
                    } else {
                        ViewStudio objViewStudio = new ViewStudio(studio);
                        mainFrame.setContentPane(objViewStudio.getContentPane());
                        mainFrame.dispose();
                    }
                }
            }
        });
    }
    public void nationalityJComboB(JComboBox nationalityIn){
        //Set nationalities in nationalityIn JComboBox
        String[] countries = Locale.getISOCountries();
        for (int i = 0; i < countries.length; i++) {
            String country = countries[i];
            Locale locale = new Locale("en", country);
            String countryName = locale.getDisplayCountry();
            nationalityIn.addItem(countryName);
        }
        nationalityIn.setSelectedIndex(-1);
    }
    public void directorJComboB(JComboBox directorReg){
        List<com.moviesapp.model.internal.Director> directorList;
        crudDirector.directorList().forEach(director -> directorReg.addItem(director.getDirectorID() + " " + director.getDirectorName()));
        directorReg.setSelectedIndex(-1);
    }
    public void studioJComboB(JComboBox studioReg){
        List<com.moviesapp.model.internal.Studio> studioList;
        crudStudio.studioList().forEach(studio -> studioReg.addItem(studio.getStudioID() + " " + studio.getStudioName()));
        studioReg.setSelectedIndex(-1);
    }
}
