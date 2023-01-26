package com.moviesapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {

    private static String user = "postgres";
    private static String pass = "jainek";

    private static Connection connection;

    public static void initConnection(){
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MovieApp",user,pass);
            System.out.println("Successful connection");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
