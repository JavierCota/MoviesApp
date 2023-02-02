package com.moviesapp.model.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {

    private static String url = "jdbc:postgresql://localhost:5432/MovieApp";
    private static String user = "postgres";
    private static String pass = "jainek";

    private static Connection connection;

    public Connection connect(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user,pass);
            System.out.println("Successful connection");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
