package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.AppLayout;
import java.util.List;

public class AppLayoutDAO extends BaseDAO<AppLayout> {

    public AppLayoutDAO() {
        super(AppLayout.class);
    }

    public AppLayout findByUserId(int userId) {
        List<AppLayout> list = findByProperty("userId", userId);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}
