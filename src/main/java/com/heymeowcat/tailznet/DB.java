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
import java.sql.Statement;

/**
 *
 * @author Meow-Meow!
 */
public class DB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tailz?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    /**
     * Creates a new database connection for each operation to avoid concurrency issues.
     * Connections are closed immediately after use in wrapper methods.
     */
    public static PreparedStatement prepare(String sql) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps;
    }

    /**
     * Execute an insert/update/delete query with automatic connection cleanup.
     */
    public static int iud(String sql) throws Exception {
        try (PreparedStatement ps = prepare(sql)) {
            return ps.executeUpdate();
        }
    }

    /**
     * Execute a search query with automatic connection and resultset cleanup.
     * Returns the ResultSet (caller must close it when done).
     */
    public static ResultSet search(String sql) throws Exception {
        try (PreparedStatement ps = prepare(sql)) {
            return ps.executeQuery();
        }
    }

    /**
     * Close a PreparedStatement and its underlying Connection.
     * Safe to call even if already closed.
     */
    public static void close(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                // ignore close failures
            }
        }
    }

    /**
     * Close a ResultSet safely.
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                // ignore close failures
            }
        }
    }

    /**
     * Close a Statement safely.
     */
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                // ignore close failures
            }
        }
    }
}
