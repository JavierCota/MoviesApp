package com.moviesapp.controller;

import com.moviesapp.model.crud.CRUDMovie;
import com.moviesapp.model.crud.CRUDDirector;
import com.moviesapp.model.crud.CRUDStudio;
import com.moviesapp.model.external.Movie;
import com.moviesapp.model.external.Director;
import com.moviesapp.model.external.Studio;
import com.moviesapp.model.util.TextFieldValidations;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.logging.Logger;

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
    Logger LOGGER = Logger.getLogger(MainView.class.getName());
    private TextFieldValidations validations = new TextFieldValidations();
    private CRUDMovie crudMovie = new CRUDMovie();
    private CRUDDirector crudDirector = new CRUDDirector();
    private CRUDStudio crudStudio = new CRUDStudio();
    private Map<Integer, String> directorsMap = readDirectors();
    private Map<Integer, String> studiosMap = readStudios();


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

        //Fills movieListTable with movies from DB
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
                LOGGER.info("Attempting to create a new movie.");
                java.sql.Date sqlDate = java.sql.Date.valueOf(releaseDateIn.getText());
                //TODO metodo valide name y ids no esten vacios - si estan bien creas movie si no no + e
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
                try {
                    crudMovie.createMovie(newMovie);
                    LOGGER.info("Movie " + newMovie.getMovieName() + " created successfully.");
                    JOptionPane.showMessageDialog(null, "Registration was completed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception exception) {
                    LOGGER.warning(exception.getMessage());
                    JOptionPane.showMessageDialog(null, "Registration was not completed " + exception.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("Attempting to create a new director.");
                java.sql.Date sqlDate = java.sql.Date.valueOf(birthDateIn.getText());
                com.moviesapp.model.external.Director newDirector = new Director(
                        directorNameIn.getText(), sqlDate, (String) nationalityIn.getSelectedItem(), activeYearsIn.getText(), favoriteGenreIn.getText());
                System.out.println(newDirector);
                directorNameIn.setText("");
                birthDateIn.setText("");
                nationalityIn.setSelectedIndex(-1);
                activeYearsIn.setText("");
                favoriteGenreIn.setText("");
                try {
                    crudDirector.createDirector(newDirector);
                    LOGGER.info("Director " + newDirector.getDirectorName() + " created successfully.");
                    JOptionPane.showMessageDialog(null, "Registration was completed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception exception) {
                    LOGGER.warning(exception.getMessage());
                    JOptionPane.showMessageDialog(null, "Registration was not completed " + exception.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerStudioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("Attempting to create a new studio.");
                java.sql.Date sqlDate = java.sql.Date.valueOf(studioFoundationIn.getText());
                com.moviesapp.model.external.Studio newStudio = new Studio(
                        studioNameIn.getText(), studioIndustryIn.getText(), sqlDate, studioFounderIn.getText(), studioHQIn.getText());
                System.out.println(newStudio);
                studioNameIn.setText("");
                studioIndustryIn.setText("");
                studioFoundationIn.setText("");
                studioFounderIn.setText("");
                studioHQIn.setText("");
                try {
                    crudStudio.createStudio(newStudio);
                    LOGGER.info("Studio " + newStudio.getStudioName() + " created successfully.");
                    JOptionPane.showMessageDialog(null, "Registration was completed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception exception) {
                    LOGGER.warning(exception.getMessage());
                    JOptionPane.showMessageDialog(null, "Registration was not completed " + exception.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = nameSearch.getText();
                LOGGER.info("Searching for " + searchSelection.getSelectedItem() + " " + search + ".");
                if (searchSelection.getSelectedItem().equals("Movie")) {
                    com.moviesapp.model.internal.Movie movie = null;
                    try {
                        movie = crudMovie.readMovie(search);
                        if (movie == null) {
                            LOGGER.warning(search + " was not found.");
                            JOptionPane.showMessageDialog(null, "Register not found ", "Message", JOptionPane.ERROR_MESSAGE);
                            nameSearch.setText("");
                        } else {
                            LOGGER.info(search + " was found. Moving to View" + searchSelection.getSelectedItem() + ".");
                            ViewMovie objViewMovie = new ViewMovie(movie);
                            mainFrame.setContentPane(objViewMovie.getContentPane());
                            mainFrame.dispose();
                        }
                    } catch (Exception exception) {
                        LOGGER.warning(exception.getMessage());
                        JOptionPane.showMessageDialog(null, "Error in system search " + exception.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (searchSelection.getSelectedItem().equals("Director")) {
                    com.moviesapp.model.internal.Director director = null;
                    try {
                        director = crudDirector.readDirector(search);
                        if (director == null) {
                            LOGGER.warning(search + " was not found.");
                            JOptionPane.showMessageDialog(null, "Register not found ", "Message", JOptionPane.ERROR_MESSAGE);
                            nameSearch.setText("");
                        } else {
                            LOGGER.info(search + " was found. Moving to View" + searchSelection.getSelectedItem() + ".");
                            ViewDirector objViewDirector = new ViewDirector(director);
                            mainFrame.setContentPane(objViewDirector.getContentPane());
                            mainFrame.dispose();
                        }
                    } catch (Exception exception) {
                        LOGGER.warning(exception.getMessage());
                        JOptionPane.showMessageDialog(null, "Error in system search " + exception.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (searchSelection.getSelectedItem().equals("Studio")) {
                    com.moviesapp.model.internal.Studio studio = null;
                    try {
                        studio = crudStudio.readStudio(search);
                        if (studio == null) {
                            LOGGER.warning(search + " was not found.");
                            JOptionPane.showMessageDialog(null, "Register not found ", "Message", JOptionPane.ERROR_MESSAGE);
                            nameSearch.setText("");
                        } else {
                            LOGGER.info(search + " was found. Moving to View" + searchSelection.getSelectedItem() + ".");
                            ViewStudio objViewStudio = new ViewStudio(studio);
                            mainFrame.setContentPane(objViewStudio.getContentPane());
                            mainFrame.dispose();
                        }
                    } catch (Exception exception) {
                        LOGGER.warning(exception.getMessage());
                        JOptionPane.showMessageDialog(null, "Error in system search " + exception.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //Validations for Movie tab.
        validations.validateStringChars(movieNameIn);
        validations.validateStringChars(genreIn);
        validations.validateIntChars(durationIn);
        validations.validateStringChars(classificationIn);
        validations.validateDateChars(releaseDateIn);
        validations.validateStringChars(descriptionIn);
        validations.validateNameLength(movieNameIn);
        validations.validateTextFLength(genreIn);
        validations.validateIntDateLength(durationIn);
        validations.validateTextFLength(classificationIn);
        validations.validateIntDateLength(releaseDateIn);
        validations.validateDescriptionLength(descriptionIn);

        //Validations for Director tab.
        validations.validateStringChars(directorNameIn);
        validations.validateDateChars(birthDateIn);
        validations.validateStringChars(activeYearsIn);
        validations.validateStringChars(favoriteGenreIn);
        validations.validateNameLength(directorNameIn);
        validations.validateIntDateLength(birthDateIn);
        validations.validateTextFLength(activeYearsIn);
        validations.validateTextFLength(favoriteGenreIn);

        //Validations for Studio tab.
        validations.validateStringChars(studioNameIn);
        validations.validateStringChars(studioIndustryIn);
        validations.validateDateChars(studioFoundationIn);
        validations.validateStringChars(studioFounderIn);
        validations.validateStringChars(studioHQIn);
        validations.validateNameLength(studioNameIn);
        validations.validateTextFLength(studioIndustryIn);
        validations.validateIntDateLength(studioFoundationIn);
        validations.validateTextFLength(studioFounderIn);
        validations.validateTextFLength(studioHQIn);

        //Validations for Search tab.
        validations.validateStringChars(nameSearch);
        validations.validateNameLength(nameSearch);
    }

    public Map<Integer, String> readDirectors() {
        Map<Integer, String> directorsMap = null;
        try {
            directorsMap = crudDirector.readDirectorsID();
            LOGGER.info(directorsMap.size() + " Directors read successfully.");
        } catch (Exception exception) {
            LOGGER.warning(exception.getMessage());
            JOptionPane.showMessageDialog(null, "Error in system search " + exception.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
        }
        return directorsMap;
    }

    public Map<Integer, String> readStudios() {
        Map<Integer, String> studiosMap = null;
        try {
            studiosMap = crudStudio.readStudiosID();
            LOGGER.info(studiosMap.size() + " Studios read successfully.");
        } catch (Exception exception) {
            LOGGER.warning(exception.getMessage());
            JOptionPane.showMessageDialog(null, "Error in system search " + exception.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
        }
        return studiosMap;
    }

    public void nationalityJComboB(JComboBox nationalityIn) {
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

    public void directorJComboB(JComboBox directorIn) {
        Set<Map.Entry<Integer, String>> set = directorsMap.entrySet();
        List<Map.Entry<Integer, String>> directorList = new ArrayList<>(set);
        directorList.sort(Comparator.comparing(Map.Entry::getValue));
        directorList.forEach(d -> directorIn.addItem(d.getValue() + " " + d.getKey()));
        directorIn.setSelectedIndex(-1);
    }

    public void studioJComboB(JComboBox studioIn) {
        Set<Map.Entry<Integer, String>> set = studiosMap.entrySet();
        List<Map.Entry<Integer, String>> studioList = new ArrayList<>(set);
        studioList.sort(Comparator.comparing(Map.Entry::getValue));
        studioList.forEach(s -> studioIn.addItem(s.getValue() + " " + s.getKey()));
        studioIn.setSelectedIndex(-1);
    }

    public Integer returnInt(String textWID) {
        int id = Integer.parseInt(textWID.replaceAll("[\\D]", ""));
        return id;
    }

    public JTable movieJTable() {
        String[][] movieArray;
        List<com.moviesapp.model.internal.Movie> movieList = null;
        try {
            movieList = crudMovie.movieList();
            LOGGER.info(movieList.size() + " Movies read successfully.");
        } catch (Exception exception) {
            LOGGER.warning(exception.getMessage());
            JOptionPane.showMessageDialog(null, "Error in system search " + exception.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
        }
        movieList.sort(Comparator.comparing(com.moviesapp.model.internal.Movie::getMovieName));
        movieArray = movieList.stream().map(m -> new String[]{
                m.getMovieName(),
                m.getGenre(),
                m.getDuration().toString(),
                m.getClassification(),
                m.getReleaseDate().toString(),
                m.getDescription(),
                m.getMovieID().toString(),
                directorsMap.get(m.getDirectorID()),
                studiosMap.get(m.getStudioID())}).toArray(String[][]::new);
        String[] columnNames = {"Name", "Genre", "Duration(min)", "Classification", "Release Date", "Description", "MovieID", "Director", "Studio"};
        JTable movieTable = new JTable(movieArray, columnNames);
        return movieTable;
    }
}
