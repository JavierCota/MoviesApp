package com.moviesapp.model.crud;

import com.moviesapp.util.DBManager;
import com.moviesapp.model.internal.Director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CRUDDirector extends DBManager {

    private PreparedStatement statement;
    private ResultSet rs;

    public CRUDDirector() {
    }

    //Create, read, update, delete.
    public void createDirector(com.moviesapp.model.external.Director director) throws SQLException {
        Connection connection = connect();
        String sql = "insert into director(name,birth_date,nationality,active_years,favorite_genre) values(?,?,?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, director.getDirectorName());
        statement.setDate(2, director.getBirthDate());
        statement.setString(3, director.getNationality());
        statement.setString(4, director.getActiveYears());
        statement.setString(5, director.getFavoriteGenre());
        statement.execute();
        statement.close();
        connection.close();
    }

    public Director readDirector(String name) throws SQLException {
        //Read from database.
        //Change return type.
        Director director = null;
        Connection connection = connect();
        String sql = "select * from director where name = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        rs = statement.executeQuery();
        while ((rs.next())) {
            director = new Director(
                    rs.getString("name"),
                    rs.getDate("birth_date"),
                    rs.getString("nationality"),
                    rs.getString("active_years"),
                    rs.getString("favorite_genre"),
                    rs.getInt("id_director"));
        }
        rs.close();
        statement.close();
        connection.close();
        return director;
    }

    public void updateDirector(Director director) throws SQLException {
        //Update in database.
        Connection connection = connect();
        String sql = "update director set name=?,birth_date=?,nationality=?,active_years=?,favorite_genre=? where id_director = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, director.getDirectorName());
        statement.setDate(2, director.getBirthDate());
        statement.setString(3, director.getNationality());
        statement.setString(4, director.getActiveYears());
        statement.setString(5, director.getFavoriteGenre());
        statement.setInt(6, director.getDirectorID());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void deleteDirector(Integer directorID) throws SQLException {
        //Delete in database.
        Connection connection = connect();
        String sql = "delete from director where id_director = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, directorID);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public Map<Integer, String> readDirectorsID() throws SQLException {
        Map<Integer, String> directorMap = new HashMap<>();
        Connection connection = connect();
        String sql = "select id_director,name from director";
        statement = connection.prepareStatement(sql);
        rs = statement.executeQuery();
        while ((rs.next())) {
            directorMap.put(rs.getInt("id_director"), rs.getString("name"));
        }
        rs.close();
        statement.close();
        connection.close();
        return directorMap;
    }
}
