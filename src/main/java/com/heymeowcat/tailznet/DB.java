/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Meow-Meow!
 */
public class DB {
    private static Connection conn;
    private static void setConnection()throws Exception{
     Class.forName("com.mysql.jdbc.Driver");
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sEXUaFqh92","root","");
        } catch (Exception e) {
           
        }
    }
    public static void iud(String sql) throws Exception{
       if(conn == null){
           setConnection();
       }
       conn.createStatement().executeUpdate(sql);
    }
    public static ResultSet search(String sql)throws Exception{
        if(conn ==null){
            setConnection();
    }
      return conn.createStatement().executeQuery(sql);
    } 

}
