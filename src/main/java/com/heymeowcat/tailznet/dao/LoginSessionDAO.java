package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.LoginSession;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSessionDAO extends BaseDAO<LoginSession> {

    private static final Logger logger = LoggerFactory.getLogger(LoginSessionDAO.class);

    public LoginSessionDAO() {
        super(LoginSession.class);
    }

    public List<LoginSession> findByUserLogin(int userLoginId) {
        return findByProperty("userLoginId", userLoginId);
    }
}
