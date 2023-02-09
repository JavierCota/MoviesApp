package com.moviesapp.model.crud;

import com.moviesapp.model.util.DBManager;
import com.moviesapp.model.internal.Director;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CRUDDirector extends DBManager {

    PreparedStatement statement;
    ResultSet rs;

    public CRUDDirector() {
    }

    //Create, read, update, delete.
    public void createDirector(com.moviesapp.model.external.Director director) {
        try {
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
            JOptionPane.showMessageDialog(null, "Registration was completed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registration was not completed " + e, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Director readDirector(String name) {
        //Read from database.
        //Change return type.
        Director director = null;
        try {
            Connection connection = connect();
            String sql = "select * from director where name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            rs = statement.executeQuery();
            while ((rs.next())){
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in system search " + e, "Search error", JOptionPane.ERROR_MESSAGE);
            return director;
        }
    }

    public void updateDirector(Director director) {
        //Update in database.
        try{
            Connection connection = connect();
            String sql = "update director set name=?,birth_date=?,nationality=?,active_years=?,favorite_genre=? where id_director = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, director.getDirectorName());
            statement.setDate(2, director.getBirthDate());
            statement.setString(3, director.getNationality());
            statement.setString(4, director.getActiveYears());
            statement.setString(5, director.getFavoriteGenre());
            statement.setInt(6,director.getDirectorID());
            statement.executeUpdate();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Register was updated successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Register was not updated " + e, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteDirector(Integer directorID) {
        //Delete in database.
        try {
            Connection connection = connect();
            String sql = "delete from director where id_director = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, directorID);
            statement.executeUpdate();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null,"The register was deleted successfully","Deleted",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"An error occurred while deleting the register "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Director> directorList(){
        Director director = null;
        List<Director> directorList = new ArrayList<Director>();
        try {
            Connection connection = connect();
            String sql = "select * from director";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while ((rs.next())){
                director = new Director(
                        rs.getString("name"),rs.getDate("birth_date") ,"" ,"" ,"",
                        rs.getInt("id_director"));

                directorList.add(director);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in system search " + e, "Search error", JOptionPane.ERROR_MESSAGE);
        }
        return directorList;
    }
    //Crear metodo que regrese lista de directores, guardar en mapa nombre y ID (Tambien para studio).
    //Crear jcombobox en mainview movie para studio y director.
}
