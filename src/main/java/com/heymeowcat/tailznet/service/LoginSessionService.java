package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.LoginSessionDAO;
import com.heymeowcat.tailznet.entities.LoginSession;

public class LoginSessionService {

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
