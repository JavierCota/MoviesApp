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
    public void createMovie(com.moviesapp.model.external.Movie movie) {
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

    public Movie readMovie(String name) {
        //Read from database.
        //Change return type.
        Movie movie = null;
        try{
            Connection connection = connect();
            String sql = "select * from movie where name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            rs = statement.executeQuery();
            while ((rs.next())){
                movie = new Movie(
                        rs.getString("name"),
                        rs.getString("genre"),
                        rs.getInt("duration_min"),
                        rs.getString("classification"),
                        rs.getDate("release_date"),
                        rs.getString("description"),
                        rs.getInt("id_movie"),
                        rs.getInt("id_director"),
                        rs.getInt("id_studio"));
            }
            rs.close();
            statement.close();
            connection.close();
            return movie;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error in system search " + e, "Search error", JOptionPane.ERROR_MESSAGE);
            return movie;
        }
    }

    public void deleteMovie(Integer movieID){
            //Delete in database.
            try {
                Connection connection = connect();
                String sql = "delete from movie where id_movie = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, movieID);
                statement.executeUpdate();
                statement.close();
                connection.close();
                JOptionPane.showMessageDialog(null,"The register was deleted successfully","Deleted",JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null,"An error occurred while deleting the register "+e,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
}
