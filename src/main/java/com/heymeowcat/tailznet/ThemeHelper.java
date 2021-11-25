package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.AppThemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThemeHelper {

    private static final Logger logger = LoggerFactory.getLogger(ThemeHelper.class);

    private static final String[] DEFAULT_COLORS = {"white", "white", "#f7f4f4", "black-text", "red lighten-5", "#f8bbd0"};

    public static String[] getThemeColors(int usersIdUsers) throws Exception {
        AppThemeService themeService = new AppThemeService();
        String themeName = themeService.getUserTheme(usersIdUsers);
        if ("default".equals(themeName)) {
            return DEFAULT_COLORS;
        }
        return getColorsForTheme(themeName);
    }

    private static String[] getColorsForTheme(String theme) {
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
                return DEFAULT_COLORS;
        }
    }
}
