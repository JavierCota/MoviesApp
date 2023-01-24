package com.moviesapp.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView{
    private JButton button1;
    private JLabel label1;
    private JPanel panel1;
    private JTextField descriptionIn;
    private JTextField releaseDateIn;
    private JTextField movieNameIn;
    private JTextField genreIn;
    private JTextField durationIn;
    private JTextField classificationIn;
    private JPanel mainPanel;

    public MainView() {

        JFrame frame = new JFrame("MovieApp");
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText("Test button1");
            }
        });
    }
}
