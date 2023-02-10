package com.moviesapp.model.crud;

import com.moviesapp.model.util.DBManager;
import com.moviesapp.model.internal.Movie;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRUDMovie extends DBManager {

    PreparedStatement statement;
    ResultSet rs;

    public CRUDMovie(){
    }

    //Create, read, update, delete.
    public void createMovie(Movie movie) {
        try {
            Connection connection = connect();
            String sql = "insert into movie(name,genre,duration_min,classification,release_date,description,id_director,id_studio) values(?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, movie.getMovieName());
            statement.setString(2, movie.getGenre());
            statement.setInt(3,movie.getDuration());
            statement.setString(4,movie.getClassification());
            statement.setDate(5,movie.getReleaseDate());
            statement.setString(6, movie.getDescription());
            statement.setInt(7,movie.getDirectorID());
            statement.setInt(8,movie.getStudioID());
            statement.execute();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Registration was completed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registration was not completed " + e, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }
}
