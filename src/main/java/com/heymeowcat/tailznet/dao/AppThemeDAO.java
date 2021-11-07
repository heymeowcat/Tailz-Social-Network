package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.AppTheme;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppThemeDAO extends BaseDAO<AppTheme> {

    private static final Logger logger = LoggerFactory.getLogger(AppThemeDAO.class);

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
