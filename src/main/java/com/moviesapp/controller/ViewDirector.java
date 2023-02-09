package com.moviesapp.controller;

import com.moviesapp.model.crud.CRUDDirector;
import com.moviesapp.model.internal.Director;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDirector extends javax.swing.JFrame {
    private JTextField viewDName;
    private JPanel panel1;
    private JLabel viewDID;
    private JTextField viewDFavGenre;
    private JTextField viewDBirthday;
    private JTextField viewDActiveYears;
    private JComboBox viewDNationality;
    private JButton viewDReturnBtn;
    private JPanel viewDPanel;
    private JButton viewDUpdateBtn;
    private JButton viewDDeleteButton;
    private MainView objMainView = new MainView();
    private CRUDDirector crudDirector = new CRUDDirector();

    public ViewDirector(Director director) {

        JFrame frame = new JFrame("MovieApp");
        frame.setContentPane(viewDPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        objMainView.nationalityJComboB(viewDNationality);

        viewDName.setText(director.getDirectorName());
        viewDBirthday.setText(director.getBirthDate().toString());
        viewDNationality.setSelectedItem(director.getNationality());
        viewDActiveYears.setText(director.getActiveYears());
        viewDFavGenre.setText(director.getFavoriteGenre());
        viewDID.setText(director.getDirectorID().toString());

        viewDReturnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objMainView = new MainView();
                frame.setContentPane(objMainView.getContentPane());
                frame.dispose();
            }
        });
        viewDDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this register?","Delete register", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    crudDirector.deleteDirector(director.getDirectorID());
                    objMainView = new MainView();
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
}
