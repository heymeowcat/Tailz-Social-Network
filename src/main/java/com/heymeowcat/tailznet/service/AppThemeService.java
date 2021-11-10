package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.AppThemeDAO;
import com.heymeowcat.tailznet.entities.AppTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppThemeService {
    private static final Logger logger = LoggerFactory.getLogger(AppThemeService.class);

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

    public void upsertTheme(int userId, String themeName) {
        AppTheme existing = getUserThemeObject(userId);
        if (existing != null) {
            existing.setThemeName(themeName);
            themeDAO.update(existing);
        } else {
            AppTheme theme = new AppTheme();
            theme.setUserId(userId);
            theme.setThemeName(themeName);
            themeDAO.save(theme);
        }
    }
}
