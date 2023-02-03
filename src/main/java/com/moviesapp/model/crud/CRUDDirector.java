package com.moviesapp.model.crud;

import com.moviesapp.model.util.DBManager;
import com.moviesapp.model.internal.Director;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CRUDDirector extends DBManager {

    PreparedStatement statement;
    public CRUDDirector(){}
    //Create, read, update, delete.
    public void createDirector(com.moviesapp.model.external.Director director){
        try{
            Connection connection = connect();
            String sql = "insert into director(name,birth_date,nationality,active_years,favorite_genre) values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, director.getDirectorName());
            statement.setDate(2,director.getBirthDate());
            statement.setString(3,director.getNationality());
            statement.setString(4,director.getActiveYears());
            statement.setString(5,director.getFavoriteGenre());
            statement.execute();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Registration was completed successfully","Message", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Registration was not completed "+e,"Message", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void readDirector(Director director){
        //Read from database.
        //Change return type.
    }
    public void updateDirector(Director director){
        //Update in database.
    }
    public void deleteDirector(Director director){
        //Delete in database.
    }
}
