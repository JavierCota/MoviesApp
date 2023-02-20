package com.moviesapp.model.crud;

import com.moviesapp.model.util.DBManager;
import com.moviesapp.model.internal.Studio;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CRUDStudio extends DBManager {

    Logger LOGGER = Logger.getLogger(CRUDStudio.class.getName());
    PreparedStatement statement;
    ResultSet rs;

    public CRUDStudio() {
    }

    public void createStudio(com.moviesapp.model.external.Studio studio) throws SQLException {
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

    public void updateStudio(Studio studio) throws SQLException {
        //Update in database.
            Connection connection = connect();
            String sql = "update studio set name=?,industry=?,foundation=?,founder=?,headquarters=? where id_studio = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, studio.getStudioName());
            statement.setString(2,studio.getIndustry());
            statement.setDate(3,studio.getFoundation());
            statement.setString(4,studio.getFounder());
            statement.setString(5,studio.getHeadquarters());
            statement.setInt(6,studio.getStudioID());
            statement.executeUpdate();
            statement.close();
            connection.close();
    }

    public void deleteStudio(Integer studioID) throws SQLException {
        //Delete in database.
            Connection connection = connect();
            String sql = "delete from studio where id_studio = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, studioID);
            statement.executeUpdate();
            statement.close();
            connection.close();
    }

    public Map<Integer,String> readStudiosID(){
        Map<Integer,String> studioMap = new HashMap<>();
        try {
            Connection connection = connect();
            String sql = "select id_studio,name from studio";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while ((rs.next())){
                studioMap.put(rs.getInt("id_studio"),rs.getString("name"));
            }
            rs.close();
            statement.close();
            connection.close();
            LOGGER.info(studioMap.size() + " Studios read successfully.");
        } catch (Exception exception) {
            LOGGER.warning(exception.getMessage());
            JOptionPane.showMessageDialog(null, "Error in system search " + exception.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
        }
        return studioMap;
    }
}
