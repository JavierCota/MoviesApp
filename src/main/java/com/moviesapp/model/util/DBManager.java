package com.moviesapp.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class DBManager {

    private static String url = "jdbc:postgresql://localhost:5432/MovieApp";
    private static String user = "postgres";
    private static String pass = "jainek";
    Logger LOGGER = Logger.getLogger(DBManager.class.getName());

    private static Connection connection;

    public Connection connect(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
        return connection;
    }

}
