package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.AppLayoutDAO;
import com.heymeowcat.tailznet.entities.AppLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLayoutService {
    private static final Logger logger = LoggerFactory.getLogger(AppLayoutService.class);

    private final AppLayoutDAO layoutDAO;

    public AppLayoutService() {
        this.layoutDAO = new AppLayoutDAO();
    }

    public AppLayout getLayout(int userId) {
        return layoutDAO.findByUserId(userId);
    }

    public void saveLayout(int userId) {
        AppLayout layout = new AppLayout();
        layout.setUserId(userId);
        layout.setLayout("1");
        layoutDAO.save(layout);
    }
}
