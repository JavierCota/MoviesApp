package com.moviesapp.controller;

import com.moviesapp.model.crud.CRUDDirector;
import com.moviesapp.model.internal.Director;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.logging.Logger;

public class ViewDirector extends javax.swing.JFrame {
    private JTextField viewDName;
    private JLabel viewDID;
    private JTextField viewDFavGenre;
    private JTextField viewDBirthday;
    private JTextField viewDActiveYears;
    private JComboBox viewDNationality;
    private JButton viewDReturnBtn;
    private JPanel viewDPanel;
    private JButton viewDUpdateBtn;
    private JButton viewDDeleteButton;
    private CRUDDirector crudDirector = new CRUDDirector();
    Logger LOGGER = Logger.getLogger(ViewDirector.class.getName());

    public ViewDirector(Director director) {

        JFrame frame = new JFrame("MovieApp");
        frame.setContentPane(viewDPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        nationalityJComboB(viewDNationality);

        viewDName.setText(director.getDirectorName());
        viewDBirthday.setText(director.getBirthDate().toString());
        viewDNationality.setSelectedItem(director.getNationality());
        viewDActiveYears.setText(director.getActiveYears());
        viewDFavGenre.setText(director.getFavoriteGenre());
        viewDID.setText(director.getDirectorID().toString());

        viewDReturnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView objMainView = new MainView();
                frame.setContentPane(objMainView.getContentPane());
                frame.dispose();
            }
        });
        viewDDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this register?","Delete register", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    LOGGER.info("Attempting to delete "+ director.getDirectorName()+ " ID: "+director.getDirectorID());
                    crudDirector.deleteDirector(director.getDirectorID());
                    MainView objMainView = new MainView();
                    frame.setContentPane(objMainView.getContentPane());
                    frame.dispose();
                }
            }
        });
        viewDUpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null,"Are you sure you want to update this register?","Update register", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    java.sql.Date sqlDate = java.sql.Date.valueOf(viewDBirthday.getText());
                    director.setDirectorName(viewDName.getText());
                    director.setBirthDate(sqlDate);
                    director.setNationality(viewDNationality.getSelectedItem().toString());
                    director.setActiveYears(viewDActiveYears.getText());
                    director.setFavoriteGenre(viewDFavGenre.getText());
                    crudDirector.updateDirector(director);
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
}
