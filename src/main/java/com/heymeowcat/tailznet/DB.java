/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Meow-Meow!
 */
public class DB {

    private static Connection conn;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    /**
     * Called once by AppContextListener on application startup to configure
     * the database connection parameters from web.xml context-params.
     */
    public static void init(String server, String port, String db,
            String username, String password) {
        dbUrl = "jdbc:mysql://" + server + ":" + port + "/" + db + "?useSSL=false";
        dbUser = username;
        dbPassword = password;
    }

    private static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }
        return conn;
    }

    public static void iud(String sql) throws Exception {
        getConnection().createStatement().executeUpdate(sql);
    }

    public static ResultSet search(String sql) throws Exception {
        return getConnection().createStatement().executeQuery(sql);
    }

    public static PreparedStatement prepare(String sql) throws Exception {
        return getConnection().prepareStatement(sql);
    }

}
