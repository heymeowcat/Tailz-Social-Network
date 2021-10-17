package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.AppTheme;
import java.util.List;

public class AppThemeDAO extends BaseDAO<AppTheme> {

    public AppThemeDAO() {
        super(AppTheme.class);
    }

    public AppTheme findByUserId(int userId) {
        List<AppTheme> list = findByProperty("userId", userId);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }

    public String getThemeName(int userId) {
        AppTheme theme = findByUserId(userId);
        return theme != null ? theme.getThemeName() : "default";
    }
}
