package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.LoginSessionDAO;
import com.heymeowcat.tailznet.entities.LoginSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSessionService {
    private static final Logger logger = LoggerFactory.getLogger(LoginSessionService.class);

    private final LoginSessionDAO sessionDAO;

    public LoginSessionService() {
        this.sessionDAO = new LoginSessionDAO();
    }

    public void logLoginSession(int loginId, String ipAddress) {
        LoginSession session = new LoginSession();
        session.setIpAddress(ipAddress);
        session.setUserLoginId(loginId);
        sessionDAO.save(session);
    }
}
