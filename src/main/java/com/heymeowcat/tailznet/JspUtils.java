package com.heymeowcat.tailznet;

/**
 * Utility class for JSP pages providing HTML escaping and theme color access.
 * Used to replace inline scriptlet code with safe, reusable methods.
 */
public class JspUtils {

    private static final String[] EMPTY_COLORS = {"", "", "", "", "", ""};

    public static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    public static String[] getThemeColors(int uid) {
        try {
            return ThemeHelper.getThemeColors(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return EMPTY_COLORS;
        }
    }

    public static String getAColor(int uid) {
        return getThemeColors(uid)[0];
    }

    public static String getBColor(int uid) {
        return getThemeColors(uid)[1];
    }

    public static String getCColor(int uid) {
        return getThemeColors(uid)[2];
    }

    public static String getDColor(int uid) {
        return getThemeColors(uid)[3];
    }

    public static String getEColor(int uid) {
        return getThemeColors(uid)[4];
    }

    public static String getFColor(int uid) {
        return getThemeColors(uid)[5];
    }
}
