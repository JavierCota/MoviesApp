package com.moviesapp.controller;

import com.moviesapp.model.crud.CRUDMovie;
import com.moviesapp.model.crud.CRUDDirector;
import com.moviesapp.model.crud.CRUDStudio;
import com.moviesapp.model.external.Movie;
import com.moviesapp.model.external.Director;
import com.moviesapp.model.external.Studio;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainView extends javax.swing.JFrame {
    private JButton registerMovieButton;
    private JLabel label1;
    private JPanel panel1;
    private JTextField descriptionIn;
    private JTextField releaseDateIn;
    private JTextField movieNameIn;
    private JTextField genreIn;
    private JTextField durationIn;
    private JTextField classificationIn;
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
    private JComboBox studioIn;
    private JComboBox directorIn;
    private JTable movieListTable;
    private JPanel movieListPanel;
    private JTable movieTable;
    private CRUDMovie crudMovie = new CRUDMovie();
    private CRUDDirector crudDirector = new CRUDDirector();
    private CRUDStudio crudStudio = new CRUDStudio();
    private Map<Integer,String> directorsMap = crudDirector.readDirectorsID();
    private Map<Integer,String> studiosMap = crudStudio.readStudiosID();


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
        directorJComboB(directorIn);

        //Set studios in studioReg JComboBox
        studioJComboB(studioIn);

        //Adds Movie List tab with MovieJTable
        movieTable = movieJTable();
        TableModel tableModel = movieTable.getModel();
        movieListTable.setModel(tableModel);

        //Set options in searchSelection JComboBox
        searchSelection.addItem("Movie");
        searchSelection.addItem("Director");
        searchSelection.addItem("Studio");
        searchSelection.setSelectedIndex(-1);

        registerMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.sql.Date sqlDate = java.sql.Date.valueOf(releaseDateIn.getText());
                com.moviesapp.model.external.Movie newMovie = new Movie(
                        movieNameIn.getText(), genreIn.getText(), Integer.parseInt(durationIn.getText()), classificationIn.getText(), sqlDate, descriptionIn.getText(),
                        returnInt((String) directorIn.getSelectedItem()), returnInt((String) studioIn.getSelectedItem()));
                System.out.println(newMovie);
                movieNameIn.setText("");
                genreIn.setText("");
                durationIn.setText("");
                classificationIn.setText("");
                releaseDateIn.setText("");
                descriptionIn.setText("");
                directorIn.setSelectedIndex(-1);
                studioIn.setSelectedIndex(-1);
                crudMovie.createMovie(newMovie);
            }
        });

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
                if (searchSelection.getSelectedItem().equals("Movie")) {
                    com.moviesapp.model.internal.Movie movie = crudMovie.readMovie(search);
                    if (movie == null) {
                        JOptionPane.showMessageDialog(null, "Register not found ", "Message", JOptionPane.ERROR_MESSAGE);
                        nameSearch.setText("");
                    } else {
                        ViewMovie objViewMovie = new ViewMovie(movie);
                        mainFrame.setContentPane(objViewMovie.getContentPane());
                        mainFrame.dispose();
                    }
                }
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
    public void directorJComboB(JComboBox directorIn){
        Set<Map.Entry<Integer,String>> set = directorsMap.entrySet();
        List<Map.Entry<Integer,String>> directorList = new ArrayList<>(set);
        directorList.sort(Comparator.comparing(Map.Entry::getValue));
        directorList.forEach(d -> directorIn.addItem(d.getValue()+ " " + d.getKey()));
        directorIn.setSelectedIndex(-1);
    }
    public void studioJComboB(JComboBox studioIn){
        Set<Map.Entry<Integer,String>> set = studiosMap.entrySet();
        List<Map.Entry<Integer,String>> studioList = new ArrayList<>(set);
        studioList.sort(Comparator.comparing(Map.Entry::getValue));
        studioList.forEach(s -> studioIn.addItem(s.getValue()+ " " + s.getKey()));
        studioIn.setSelectedIndex(-1);
    }
    public Integer returnInt(String textWID){
        int id = Integer.parseInt(textWID.replaceAll("[\\D]", ""));
        return id;
    }
    public JTable movieJTable() {
        String[][] movieArray;
        List<com.moviesapp.model.internal.Movie> movieList;
        movieList = crudMovie.movieList();
        movieList.sort(Comparator.comparing(com.moviesapp.model.internal.Movie::getMovieName));
        movieArray = movieList.stream().map(m -> new String[] {
                m.getMovieName(),
                m.getGenre(),
                m.getDuration().toString(),
                m.getClassification(),
                m.getReleaseDate().toString(),
                m.getDescription(),
                m.getMovieID().toString(),
                directorsMap.get(m.getDirectorID()),
                studiosMap.get(m.getStudioID())}).toArray(String[][]::new);
        String[] columnNames = {"Name","Genre","Duration(min)","Classification","Release Date","Description","MovieID","Director","Studio"};
        JTable movieTable = new JTable(movieArray,columnNames);
        return movieTable;
    }
}
