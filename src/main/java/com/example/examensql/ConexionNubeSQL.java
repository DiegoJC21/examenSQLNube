package com.example.examensql;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionNubeSQL {
    Connection conn;

    public Connection open(){
        String url="jdbc:mysql://u19mznlmeo8rh6tu:SekrfFpFJp5lFFCLrYHK@baibnet1agg8sb48afna-mysql.services.clever" +
                "-cloud.com:3306/baibnet1agg8sb48afna";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(url);
            return conn;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void close(){
        if (conn!=null){
            try{
                conn.close();
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("Exception controlada.");
            }
        }
    }
}
