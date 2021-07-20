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
public class ThemeHelper {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tailz?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    /**
     * Returns theme colors based on the user's theme preference.
     * The returned array contains: [Acolor, Bcolor, Ccolor, Dcolor, Ecolor, Fcolor]
     * @param usersIdUsers the user's ID
     * @return array of theme color strings
     * @throws Exception if database access fails
     */
    public static String[] getThemeColors(int usersIdUsers) throws Exception {
        String[] colors = {"white", "white", "#f7f4f4", "black-text", "red lighten-5", "#f8bbd0"};
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT themename FROM app_theme WHERE users_idusers=?")) {
            
            ps.setInt(1, usersIdUsers);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String theme = rs.getString(1);
                    switch (theme) {
                        case "pinkdark":
                        case "dark":
                            return new String[]{"black", "pink", "#1c1c1c", "white-text", "grey darken-4", "#e91e63"};
                        case "pinklight":
                        case "light":
                            return new String[]{"white", "pink lighten-4", "#f7f4f4", "black-text", "red lighten-5", "#f8bbd0"};
                        case "bluelight":
                            return new String[]{"white", "light-blue lighten-2", "#f7f4f4", "black-text", "light-blue lighten-5", "#4fc3f7"};
                        case "bluedark":
                            return new String[]{"black", "blue", "#1c1c1c", "white-text", "grey darken-4", "#2196F3"};
                        case "yellowlight":
                            return new String[]{"white", "yellow lighten-2", "#f7f4f4", "black-text", "yellow lighten-4", "#fff176"};
                        case "yellowdark":
                            return new String[]{"black", "yellow darken-4", "#1c1c1c", "white-text", "grey darken-4", "#f57f17"};
                        case "greenlight":
                            return new String[]{"white", "light-green lighten-2", "#f7f4f4", "black-text", "light-green lighten-4", "#aed581"};
                        case "greendark":
                            return new String[]{"black", "green", "#1c1c1c", "white-text", "grey darken-4", "#4CAF50"};
                        case "purplelight":
                            return new String[]{"white", "purple lighten-3", "#f7f4f4", "black-text", "purple lighten-5", "#ce93d8"};
                        case "purpledark":
                            return new String[]{"black", "purple", "#1c1c1c", "white-text", "grey darken-4", "#9c27b0"};
                        default:
                            return colors;
                    }
                }
            }
        }
        return colors;
    }
}