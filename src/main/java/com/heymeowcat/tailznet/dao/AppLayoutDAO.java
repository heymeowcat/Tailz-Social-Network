package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.AppLayout;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLayoutDAO extends BaseDAO<AppLayout> {

    private static final Logger logger = LoggerFactory.getLogger(AppLayoutDAO.class);

    public AppLayoutDAO() {
        super(AppLayout.class);
    }

    public AppLayout findByUserId(int userId) {
        List<AppLayout> list = findByProperty("userId", userId);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}
