package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.UserLoginDAO;
import com.heymeowcat.tailznet.entities.UserLogin;
import com.heymeowcat.tailznet.PasswordUtil;

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
        return PasswordUtil.checkPassword(password, login.getPassword());
    }

    public void saveLogin(UserLogin login) {
        login.setPassword(PasswordUtil.hashPassword(login.getPassword()));
        loginDAO.save(login);
    }

    public void updateLogin(UserLogin login) {
        loginDAO.update(login);
    }

    public void updatePassword(int userId, String password) {
        UserLogin login = loginDAO.findByUserId(userId);
        if (login != null) {
            login.setPassword(PasswordUtil.hashPassword(password));
            loginDAO.update(login);
        }
    }

    public UserLogin createLogin(int userId, String username, String password) {
        UserLogin login = new UserLogin();
        login.setUserId(userId);
        login.setUsername(username);
        login.setPassword(PasswordUtil.hashPassword(password));
        loginDAO.save(login);
        return login;
    }

    public int getLoginIdByUserId(int userId) {
        return loginDAO.getLoginIdByUserId(userId);
    }
}
