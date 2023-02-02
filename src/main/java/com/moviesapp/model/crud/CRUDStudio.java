package com.moviesapp.model.crud;

import com.moviesapp.model.util.DBManager;
import com.moviesapp.model.internal.Studio;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CRUDStudio {
    public CRUDStudio(){}
    DBManager conn = new DBManager();

    public void createStudio(com.moviesapp.model.external.Studio studio) {
        try {
            Connection connection = conn.connect();
            String sql = "insert into studio(name,industry,foundation,founder,headquarters) values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,studio.getStudioName());
            statement.setString(2,studio.getIndustry());
            statement.setDate(3,studio.getFoundation());
            statement.setString(4,studio.getFounder());
            statement.setString(5, studio.getHeadquarters());
            statement.execute();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Registration was completed successfully","Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registration was not completed "+e,"Message", JOptionPane.ERROR_MESSAGE);
        }
    }
}
