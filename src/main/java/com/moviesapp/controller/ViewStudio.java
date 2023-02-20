package com.moviesapp.controller;

import com.moviesapp.model.crud.CRUDStudio;
import com.moviesapp.model.internal.Studio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class ViewStudio extends javax.swing.JFrame {
    private JTextField viewSName;
    private JPanel panel1;
    private JTextField viewSIndustry;
    private JTextField viewSFoundation;
    private JTextField viewSFounder;
    private JTextField viewSHQ;
    private JLabel viewSID;
    private JButton viewSReturnBtn;
    private JPanel viewSPanel;
    private JButton viewSUpdateBtn;
    private JButton viewSDeleteBtn;
    private CRUDStudio crudStudio = new CRUDStudio();
    Logger LOGGER = Logger.getLogger(ViewStudio.class.getName());

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
        viewSDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this register?","Delete register", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    LOGGER.info("Attempting to delete " + studio.getStudioName() + " ID: " + studio.getStudioID());
                    try{
                        crudStudio.deleteStudio(studio.getStudioID());
                        LOGGER.info("Studio deleted successfully.");
                        JOptionPane.showMessageDialog(null,"The register was deleted successfully","Deleted",JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception exception){
                        LOGGER.warning(exception.getMessage());
                        JOptionPane.showMessageDialog(null,"An error occurred while deleting the register "+exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                    MainView objMainView = new MainView();
                    frame.setContentPane(objMainView.getContentPane());
                    frame.dispose();
                }
            }
        });
        viewSUpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null,"Are you sure you want to update this register?","Update register", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    LOGGER.info("Attempting to update " + studio.getStudioName() + " ID: " + studio.getStudioID());
                    java.sql.Date sqlDate = java.sql.Date.valueOf(viewSFoundation.getText());
                    studio.setStudioName(viewSName.getText());
                    studio.setIndustry(viewSIndustry.getText());
                    studio.setFoundation(sqlDate);
                    studio.setFounder(viewSFounder.getText());
                    studio.setHeadquarters(viewSHQ.getText());
                    try{
                        crudStudio.updateStudio(studio);
                        LOGGER.info("Studio updated successfully.");
                        JOptionPane.showMessageDialog(null, "Register was updated successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception exception){
                        LOGGER.warning(exception.getMessage());
                        JOptionPane.showMessageDialog(null, "Register was not updated " + exception.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
