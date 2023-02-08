package com.moviesapp.model.crud;

import com.moviesapp.model.util.DBManager;
import com.moviesapp.model.internal.Studio;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRUDStudio extends DBManager {

    PreparedStatement statement;
    ResultSet rs;

    public CRUDStudio() {
    }

    DBManager conn = new DBManager();

    public void createStudio(com.moviesapp.model.external.Studio studio) {
        try {
            Connection connection = connect();
            String sql = "insert into studio(name,industry,foundation,founder,headquarters) values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studio.getStudioName());
            statement.setString(2, studio.getIndustry());
            statement.setDate(3, studio.getFoundation());
            statement.setString(4, studio.getFounder());
            statement.setString(5, studio.getHeadquarters());
            statement.execute();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Registration was completed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registration was not completed " + e, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Studio readStudio(String name) {
        //Read from database.
        //Change return type.
        Studio studio = null;
        try {
            Connection connection = connect();
            String sql = "select * from studio where name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            rs = statement.executeQuery();
            while ((rs.next())) {
                studio = new Studio(
                        rs.getString("name"),
                        rs.getString("industry"),
                        rs.getDate("foundation"),
                        rs.getString("founder"),
                        rs.getString("headquarters"),
                        rs.getInt("id_studio"));
            }
            rs.close();
            statement.close();
            connection.close();
            return studio;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in system search " + e, "Search error", JOptionPane.ERROR_MESSAGE);
            return studio;
        }
    }
}
