package com.moviesapp.controller;

import com.moviesapp.model.internal.Studio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewStudio extends javax.swing.JFrame {
    private JTextField viewSName;
    private JPanel panel1;
    private JTextField viewSIndustry;
    private JTextField viewSFoundation;
    private JTextField viewSFounder;
    private JTextField viewSHQ;
    private JTextField viewSID;
    private JButton viewSReturnBtn;
    private JPanel viewSPanel;

    public ViewStudio(Studio studio) {

        JFrame frame = new JFrame("MovieApp");
        frame.setContentPane(viewSPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        viewSName.setText(studio.getStudioName());
        viewSIndustry.setText(studio.getIndustry());
        viewSFoundation.setText(studio.getFoundation().toString());
        viewSFounder.setText(studio.getFounder());
        viewSHQ.setText(studio.getHeadquarters());
        viewSID.setText(studio.getStudioID().toString());

        viewSReturnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView objMainView = new MainView();
                frame.setContentPane(objMainView.getContentPane());
                frame.dispose();
            }
        });
    }
}
