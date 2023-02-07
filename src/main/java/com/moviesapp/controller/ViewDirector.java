package com.moviesapp.controller;

import com.moviesapp.model.internal.Director;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDirector extends javax.swing.JFrame{
    private JTextField viewDName;
    private JPanel panel1;
    private JTextField viewDID;
    private JTextField viewDFavGenre;
    private JTextField viewDBirthday;
    private JTextField viewDActiveYears;
    private JTextField viewDNationality;
    private JButton viewDReturnBtn;
    private JPanel viewDPanel;

    public ViewDirector(Director director) {

        JFrame frame = new JFrame("MovieApp");
        frame.setContentPane(viewDPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

            viewDName.setText(director.getDirectorName());
            viewDBirthday.setText(director.getBirthDate().toString());
            viewDNationality.setText(director.getNationality());
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
    }
}
