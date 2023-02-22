package com.moviesapp.model.crud;

import com.moviesapp.model.util.DBManager;
import com.moviesapp.model.internal.Movie;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CRUDMovie extends DBManager {

    Logger LOGGER = Logger.getLogger(CRUDMovie.class.getName());
    PreparedStatement statement;
    ResultSet rs;

    public CRUDMovie(){
    }

    //Create, read, update, delete.
    public void createMovie(com.moviesapp.model.external.Movie movie) throws SQLException {
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
    }

    public Movie readMovie(String name) throws SQLException {
        //Read from database.
        //Change return type.
        Movie movie = null;
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
    }

    public void updateMovie(Movie movie) throws SQLException {
        //Update in database.
            Connection connection = connect();
            String sql = "update movie set name=?,genre=?,duration_min=?,classification=?,release_date=?,description=? where id_movie = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, movie.getMovieName());
            statement.setString(2,movie.getGenre());
            statement.setInt(3,movie.getDuration());
            statement.setString(4, movie.getClassification());
            statement.setDate(5,movie.getReleaseDate());
            statement.setString(6, movie.getDescription());
            statement.setInt(7,movie.getMovieID());
            statement.executeUpdate();
            statement.close();
            connection.close();
    }

    public void deleteMovie(Integer movieID) throws SQLException {
        //Delete in database.
        Connection connection = connect();
        String sql = "delete from movie where id_movie = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, movieID);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public List<Movie> movieList() {
        Movie movie = null;
        List<Movie> movieList = new ArrayList<>();
        try{
            Connection connection = connect();
            String sql = "select * from movie";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while ((rs.next())){
                movie = new Movie(rs.getString("name"),
                        rs.getString("genre"),
                        rs.getInt("duration_min"),
                        rs.getString("classification"),
                        rs.getDate("release_date"),
                        "",
                        rs.getInt("id_movie"),
                        rs.getInt("id_director"),
                        rs.getInt("id_studio"));

                movieList.add(movie);
            }
            rs.close();
            statement.close();
            connection.close();
            LOGGER.info(movieList.size() + " Movies read successfully.");
        }catch (Exception exception){
            LOGGER.warning(exception.getMessage());
            JOptionPane.showMessageDialog(null, "Error in system search " + exception.getMessage(), "Search error", JOptionPane.ERROR_MESSAGE);
        }
        return movieList;
    }
}
