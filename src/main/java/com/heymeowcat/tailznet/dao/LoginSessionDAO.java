package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.LoginSession;
import java.util.List;

public class LoginSessionDAO extends BaseDAO<LoginSession> {

    public LoginSessionDAO() {
        super(LoginSession.class);
    }

    public List<LoginSession> findByUserLogin(int userLoginId) {
        return findByProperty("userLoginId", userLoginId);
    }
}
