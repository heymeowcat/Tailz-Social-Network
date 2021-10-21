package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.AppThemeDAO;
import com.heymeowcat.tailznet.entities.AppTheme;

public class AppThemeService {

    private final AppThemeDAO themeDAO;

    public AppThemeService() {
        this.themeDAO = new AppThemeDAO();
    }

    public String getUserTheme(int userId) {
        return themeDAO.getThemeName(userId);
    }

    public AppTheme getUserThemeObject(int userId) {
        return themeDAO.findByUserId(userId);
    }

    public void saveTheme(AppTheme theme) {
        themeDAO.save(theme);
    }

    public void updateTheme(AppTheme theme) {
        themeDAO.update(theme);
    }
}
