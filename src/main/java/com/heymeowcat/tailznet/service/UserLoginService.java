package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.UserLoginDAO;
import com.heymeowcat.tailznet.entities.UserLogin;

public class UserLoginService {

    private final UserLoginDAO loginDAO;

    public UserLoginService() {
        this.loginDAO = new UserLoginDAO();
    }

    public UserLogin getLoginByUsername(String username) {
        return loginDAO.findByUsername(username);
    }

    public UserLogin getLoginByUserId(int userId) {
        return loginDAO.findByUserId(userId);
    }

    public boolean authenticate(String username, String password) {
        UserLogin login = getLoginByUsername(username);
        if (login == null) return false;
        return login.getPassword().equals(password);
    }

    public void saveLogin(UserLogin login) {
        loginDAO.save(login);
    }

    public void updateLogin(UserLogin login) {
        loginDAO.update(login);
    }

    public void updatePassword(int userId, String password) {
        UserLogin login = loginDAO.findByUserId(userId);
        if (login != null) {
            login.setPassword(password);
            loginDAO.update(login);
        }
    }
}
